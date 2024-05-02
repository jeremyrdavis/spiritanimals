package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class FeedbackServiceTest {

    static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceTest.class);

    @Inject
    FeedbackServiceImpl feedbackService;

    @InjectSpy
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
        ArgumentCaptor<Feedback> feedbackCaptor = ArgumentCaptor.forClass(Feedback.class);
        Mockito.verify(feedbackRepository, Mockito.times(1)).persist(feedbackCaptor.capture());
    }

    @Test
    public void testAllFeedback() {
        LOGGER.info("Testing analyzeFeedback");
        List<FeedbackRecord> feedback = feedbackService.allFeedback();
        Mockito.verify(feedbackRepository, Mockito.times(1)).listAll();
    }
}
