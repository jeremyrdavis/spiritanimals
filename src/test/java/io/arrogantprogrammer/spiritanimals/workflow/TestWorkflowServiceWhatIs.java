package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TestWorkflowServiceWhatIs {

    static final Logger LOGGER = LoggerFactory.getLogger(TestWorkflowServiceWhatIs.class);

    @Inject
    WorkflowServiceImpl workflowService;

    @InjectMock
    WorkflowRespository  workflowRespository;

    @InjectMock
    WorkflowAIService workflowAIService;


    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(workflowRespository.findById(any(Long.class))).thenReturn(new Workflow(
                new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false),
                null,
                null,
                null,
                null,
                false,
                null
        ));
        Mockito.when(workflowAIService.whatIs(any(String.class),any(String.class))).thenReturn(WorkflowTestUtils.WHAT_IS_A_MOOSE);
        ArgumentCaptor<Workflow> argumentCaptor = ArgumentCaptor.forClass(Workflow.class);
        doNothing().when(workflowRespository).persist(argumentCaptor.capture());
    }

    @AfterEach
    public void tearDown() {
        Log.infof("Tearing down test");
        Mockito.reset(workflowRespository);
        Mockito.reset(workflowAIService);
    }

    @Test
    @Transactional
    public void testWhatIs() {
        WorkflowRecord whatIsWorkflow = workflowService.whatIs(2L);
        assertNotNull(whatIsWorkflow.whatIs());
        assertTrue(whatIsWorkflow.whatIs().isPresent());
    }
}
