package io.arrogantprogrammer.spiritanimals.dashboard;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class DashboardSpiritAnimalsTest {

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

    }

    @Test
    public void testGetSpiritAnimalById() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/spiritanimals/1")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is("Lucy"));
    }

    @Test
    public void testGeAlSpiritAnimals() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/spiritanimals/")
                .then()
                .statusCode(200).body("$", hasSize(3));
    }

}
