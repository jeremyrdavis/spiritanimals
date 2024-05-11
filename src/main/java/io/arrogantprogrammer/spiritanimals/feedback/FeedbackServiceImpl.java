package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class FeedbackServiceImpl implements FeedbackService {

    static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Inject
    FeedbackRepository feedbackRepository;

    @Inject
    FeedbackAiClient feedbackAiClient;

    @Override @Transactional
    public void processFeedback(FeedbackRecord feedbackRecord) {
        Log.infof("Processing feedback: %s", feedbackRecord);
        FeedbackRecord result = feedbackAiClient.analyze(feedbackRecord.workflowId(), feedbackRecord.feedback());
        Log.infof("Received: %s", result);
        Feedback feedback = new Feedback(feedbackRecord.workflowId(), feedbackRecord.feedback(), result.sentiment());
        feedbackRepository.persist(feedback);
        Log.infof("Feedback persisted: %s", result);
    }

    @Override
    public List<FeedbackRecord> allFeedback() {
        return feedbackRepository.listAll().stream().map(feedback -> {
            return new FeedbackRecord(feedback.getWorkflowId(), feedback.getFeedback(), feedback.getSentiment());
        }).toList();
    }
}
