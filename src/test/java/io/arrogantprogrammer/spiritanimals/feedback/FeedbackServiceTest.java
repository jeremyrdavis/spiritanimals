package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

    @InjectMock
    FeedbackAiClient feedbackAiClient;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        Mockito.when(feedbackAiClient.analyze(1L, "I loved it!"))
                .thenReturn(new Feedback(1L, "I loved it!", SENTIMENT.NEGATIVE));
    }

    @Test @Transactional
    public void testProcessFeedback() {
        LOGGER.info("Testing processFeedback");
        feedbackService.processFeedback(new FeedbackRecord(1L, "I loved it!"));
        PanacheQuery<Feedback> feedback = feedbackRepository.find("workflowId", 1L);
        assert feedback.count() == 1;
        assertEquals("I loved it!", feedback.firstResult().getFeedback());
    }

}
