package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class FeedbackResourceTest {

    @InjectMock
    FeedbackService feedbackService;

    @BeforeEach
    public void setup() {
        Mockito.when(feedbackService.allFeedback()).thenReturn(Arrays.asList(
                new FeedbackRecord(1L, "This is a test feedback"),
                new FeedbackRecord(2L, "This is another test feedback")
        ));
        Mockito.doNothing().when(feedbackService).processFeedback(any(FeedbackRecord.class));
    }

    @Test
    public void testAllFeedback() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/feedback/")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("$", hasSize(2));
    }

}
