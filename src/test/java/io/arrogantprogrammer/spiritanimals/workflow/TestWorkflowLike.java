package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TestWorkflowLike {
    @Inject
    WorkflowServiceImpl workflowService;
    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @InjectSpy
    WorkflowRespository workflowRespository;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(workflowRespository.findById(anyLong())).thenReturn(WorkflowTestUtils.WORKFLOW);
        doNothing().when(workflowRespository).persist(any(Workflow.class));
    }

    @Test
    @Transactional
    public void testLiked() {
        Log.infof("Testing liked");
        WorkflowRecord likedWorkflow = workflowService.like(2L);
        assertTrue(likedWorkflow.liked());
        Mockito.verify(workflowRespository).persist(any(Workflow.class));
    }
}
