package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class FeedbackServiceTest {

    static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceTest.class);

    @Inject
    FeedbackServiceImpl feedbackService;

    @Inject
    FeedbackRepository feedbackRepository;

    @Test
    public void testProcessFeedback() {
        LOGGER.info("Testing processFeedback");
        feedbackService.processFeedback(new FeedbackRecord(1L, "I loved it!"));
        PanacheQuery<Feedback> feedback = feedbackRepository.find("workflowId", 1L);
        assert feedback.count() == 1;
        assertEquals("I loved it!", feedback.firstResult().getFeedback());
    }

}
