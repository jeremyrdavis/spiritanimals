package io.arrogantprogrammer.spiritanimals.feedback.api;

import io.arrogantprogrammer.spiritanimals.feedback.Feedback;

import java.util.List;

public interface FeedbackService {

    void processFeedback(FeedbackRecord feedbackRecord);

    List<FeedbackRecord> allFeedback();
}
