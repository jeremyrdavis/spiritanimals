package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.feedback.FeedbackAiClient;
import io.arrogantprogrammer.spiritanimals.feedback.SENTIMENT;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@QuarkusTest
public class TestWorkflowServiceFeedback {

    @InjectMock
    WorkflowRespository workflowRespository;

    @Inject
    WorkflowServiceImpl workflowService;

    @InjectMock
    FeedbackService feedbackService;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        doNothing().when(feedbackService).processFeedback(any(FeedbackRecord.class));

        when(workflowRespository.findById(any(Long.class))).thenReturn(new Workflow(
                new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false),
                WorkflowTestUtils.WHAT_IS_A_MOOSE,
                WorkflowTestUtils.MOOSE_POEM,
                null,
                false,
                null
        ));
        doNothing().when(workflowRespository).persist(any(Workflow.class));
    }

    @Test
    @Transactional
    public void testFeedback() {
        Log.infof("Testing feedback");
        WorkflowRecord workflow = workflowService.feedback(237L, WorkflowTestUtils.FEEDBACK_TEXT);
        assertNotNull(workflow);
        assertEquals(WorkflowTestUtils.FEEDBACK_TEXT, workflow.feedback().get());
    }


}
