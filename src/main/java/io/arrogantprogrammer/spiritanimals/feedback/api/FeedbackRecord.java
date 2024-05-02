package io.arrogantprogrammer.spiritanimals.feedback.api;

import io.arrogantprogrammer.spiritanimals.feedback.SENTIMENT;

public record FeedbackRecord(Long workflowId, String feedback, SENTIMENT sentiment) {

    public FeedbackRecord(Long workflowId, String feedback) {
        this(workflowId, feedback, null);
    }
}
