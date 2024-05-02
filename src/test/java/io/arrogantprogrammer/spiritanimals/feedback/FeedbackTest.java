package io.arrogantprogrammer.spiritanimals.feedback;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTest {

    @Test
    public void testFeedback() {
        Feedback feedback01 = new Feedback(1L, "I loved it!", SENTIMENT.NEGATIVE);
        assertEquals(1L, feedback01.getWorkflowId());
        assertEquals("I loved it!", feedback01.getFeedback());
        assertEquals(SENTIMENT.NEGATIVE, feedback01.getSentiment());

        Feedback feedback02 = new Feedback(2L, "I hated it!", SENTIMENT.POSITIVE);
        assertEquals(2L, feedback02.getWorkflowId());
        assertEquals("I hated it!", feedback02.getFeedback());
        assertEquals(SENTIMENT.POSITIVE, feedback02.getSentiment());

        Feedback feedback03 = new Feedback(3L, "I was indifferent about it.", SENTIMENT.NEUTRAL);
        assertEquals(3L, feedback03.getWorkflowId());
        assertEquals("I was indifferent about it.", feedback03.getFeedback());
        assertEquals(SENTIMENT.NEUTRAL, feedback03.getSentiment());
    }
}
