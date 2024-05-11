package io.arrogantprogrammer.spiritanimals.dashboard;

import io.arrogantprogrammer.spiritanimals.feedback.SENTIMENT;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
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

        Mockito.when(feedbackService.allPositiveFeedback()).thenReturn(Arrays.asList(
                new FeedbackRecord(1L, "Great job!", SENTIMENT.POSITIVE),
                new FeedbackRecord(2L, "Great job!", SENTIMENT.POSITIVE),
                new FeedbackRecord(3L, "Great job!", SENTIMENT.POSITIVE)));

        Mockito.when(feedbackService.allNegativeFeedback()).thenReturn(Arrays.asList(
                new FeedbackRecord(1L, "I hated it", SENTIMENT.NEGATIVE),
                new FeedbackRecord(2L, "This was dumb", SENTIMENT.NEGATIVE),
                new FeedbackRecord(3L, "I'm not a bilge rat", SENTIMENT.NEGATIVE),
                new FeedbackRecord(4L, "I'm not a viper", SENTIMENT.NEGATIVE)));

        Mockito.when(feedbackService.allNeutralFeedback()).thenReturn(Arrays.asList(
                new FeedbackRecord(1L, "What did I see?", SENTIMENT.POSITIVE),
                new FeedbackRecord(2L, "Huh", SENTIMENT.POSITIVE),
                new FeedbackRecord(3L, ";)", SENTIMENT.POSITIVE),
                new FeedbackRecord(4L, "I'm not sure", SENTIMENT.NEUTRAL),
                new FeedbackRecord(5L, "I'm not sure", SENTIMENT.UNDETERMINED),
                new FeedbackRecord(6L, "I'm not sure", SENTIMENT.UNDETERMINED)));
                ;
    }
    @Test
    public void testGetAllFeedback() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/feedback")
                .then()
                .statusCode(200).body("$", hasSize(5));
    }

    @Test
    public void testGetAllPositiveFeedback() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/feedback/positive")
                .then()
                .statusCode(200).body("$", hasSize(3));
    }

    @Test
    public void testGetAllNegativeFeedback() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/feedback/negative")
                .then()
                .statusCode(200).body("$", hasSize(4));
    }

    @Test
    public void testGetAllNeutralFeedback() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/feedback/neutral")
                .then()
                .statusCode(200).body("$", hasSize(6));
    }

}
