package io.arrogantprogrammer.spiritanimals.feedback;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class FeedbackAiClientTest {

    static final Logger LOGGER = LoggerFactory.getLogger(FeedbackAiClientTest.class);

    @Inject
    FeedbackAiClient feedbackAiClient;

    @Test
    public void testAiClient() {
        Feedback result = FeedbackAiClient.fallback(2L, "I loved it!");
        assertNotNull(result);
        assertEquals(SENTIMENT.UNDETERMINED, result.getSentiment());
    }
}
