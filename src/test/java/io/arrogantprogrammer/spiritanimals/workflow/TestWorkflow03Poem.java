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
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TestWorkflow03Poem {

    @Inject
    WorkflowServiceImpl workflowService;
    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @InjectMock
    WorkflowAIService  workflowAIService;

    @InjectSpy
    WorkflowRespository workflowRespository;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty"))
                .thenReturn(new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false));

        Mockito.when(workflowRespository.findById(anyLong())).thenReturn(WorkflowTestUtils.WORKFLOW);
        Mockito.when(workflowAIService.writeAPoem(anyString(), anyString())).thenReturn(WorkflowTestUtils.MOOSE_POEM);
        doNothing().when(workflowRespository).persist(Mockito.any(Workflow.class));
    }

    @AfterEach
    public void tearDown() {
        Log.infof("Tearing down test");
        Mockito.reset(workflowRespository);
        Mockito.reset(workflowAIService);
    }

    @Test
    @Transactional
    public void testPoem() {
        WorkflowRecord poemWorkflow = workflowService.writeAPoem(1L);
        assertNotNull(poemWorkflow.poem());
        assertEquals(poemWorkflow.poem().get(), WorkflowTestUtils.MOOSE_POEM);
    }

}
