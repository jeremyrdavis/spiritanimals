package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class FeedbackServiceAllFeedbackTest {

    @InjectMock
    FeedbackRepository feedbackRepository;

    @Inject
    FeedbackService feedbackService;

    @BeforeEach
    public void setUp() {
        Mockito.when(feedbackRepository.listAll()).thenReturn(Arrays.asList(
                new Feedback(1L, "This is a test feedback"),
                new Feedback(2L, "This is another test feedback")
        ));
    }

    @Test
    public void testAllFeedback() {
        List<FeedbackRecord> allFeedback = feedbackService.allFeedback();
        assertNotNull(allFeedback);
        assertEquals(2, allFeedback.size());
    }

}
