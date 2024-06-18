package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TestWorkflowServiceLike {

    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @InjectMock
    WorkflowRespository workflowRespository;

    @Inject
    WorkflowServiceImpl workflowService;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(workflowRespository.findById(any(Long.class))).thenReturn(new Workflow(
                new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false),
                WorkflowTestUtils.WHAT_IS_A_MOOSE,
                WorkflowTestUtils.MOOSE_POEM,
                null,
                null,
                false,
                null
        ));
        doNothing().when(workflowRespository).persist(any(Workflow.class));
        doNothing().when(spiritAnimalService).like(any(Long.class));
    }

    @AfterEach
    public void tearDown() {
        Log.infof("Tearing down test");
    }

    @Test
    @Transactional
    public void testLike() {
        Log.infof("Testing liked");
        WorkflowRecord likedWorkflow = workflowService.like(2L);
        assertTrue(likedWorkflow.liked());
        Mockito.verify(spiritAnimalService, Mockito.times(1)).like(any(Long.class));
    }


}
