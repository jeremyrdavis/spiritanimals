package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FeedbackServiceImpl implements FeedbackService {

    static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Inject
    FeedbackRepository feedbackRepository;

    @Inject
    FeedbackAiClient feedbackAiClient;

    @Override @Transactional
    public void processFeedback(FeedbackRecord feedbackRecord) {
        LOGGER.info("Processing feedback: {}", feedbackRecord);
        Feedback result = feedbackAiClient.analyze(feedbackRecord.workflowId(), feedbackRecord.feedback());
        LOGGER.info("Received: {}", result);
        feedbackRepository.persist(result);
        LOGGER.info("Feedback persisted: {}", result);
    }
}
