package io.arrogantprogrammer.spiritanimals.dashboard;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.feedback.SENTIMENT;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class DashboardResourceTest {

    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @InjectMock
    FeedbackService feedbackService;

    @BeforeEach
    public void setUp() {
         Log.infof("Setting up test");

        Mockito.when(spiritAnimalService.getSpiritAnimalById(1L))
                .thenReturn(new SpiritAnimalRecord(1L, "Lucy", "Reef Shark", false));

        Mockito.when(spiritAnimalService.allSpiritAnimals())
                .thenReturn(Arrays.asList(
                        new SpiritAnimalRecord(1L, "Lucy", "Reef Shark", false),
                        new SpiritAnimalRecord(2L, "Snoopy", "Polar Bear", false),
                        new SpiritAnimalRecord(3L, "Charlie Brown", "Ferret", false)));              ;

        Mockito.when(feedbackService.allFeedback()).thenReturn(Arrays.asList(
                new FeedbackRecord(1L, "Great job!", SENTIMENT.POSITIVE),
                new FeedbackRecord(2L, "Not so good", SENTIMENT.NEGATIVE),
                new FeedbackRecord(3L, "I'm not sure", SENTIMENT.NEUTRAL),
                new FeedbackRecord(4L, "I'm not sure", SENTIMENT.NEUTRAL),
                new FeedbackRecord(5L, "I'm not sure", SENTIMENT.UNDETERMINED)));
    }

    @Test
    public void testGetById() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/1")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is("Lucy"));
    }

    @Test
    public void testGetByAll() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/")
                .then()
                .statusCode(200).body("$", hasSize(3));
    }

    @Test
    public void testGetAllFeedback() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/feedback")
                .then()
                .statusCode(200).body("$", hasSize(5));
    }
}
