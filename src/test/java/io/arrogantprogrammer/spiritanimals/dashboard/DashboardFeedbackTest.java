package io.arrogantprogrammer.spiritanimals.dashboard;

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

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class DashboardFeedbackTest {

    @InjectMock
    FeedbackService feedbackService;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(feedbackService.allFeedback()).thenReturn(Arrays.asList(
                new FeedbackRecord(1L, "Great job!", SENTIMENT.POSITIVE),
                new FeedbackRecord(2L, "Not so good", SENTIMENT.NEGATIVE),
                new FeedbackRecord(3L, "I'm not sure", SENTIMENT.NEUTRAL),
                new FeedbackRecord(4L, "I'm not sure", SENTIMENT.NEUTRAL),
                new FeedbackRecord(5L, "I'm not sure", SENTIMENT.UNDETERMINED)));
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
