package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.workflow.api.SpiritAnimalWorkflow;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TestWorkflow04AddToPoem {

    static final Logger LOGGER = LoggerFactory.getLogger(TestWorkflow04AddToPoem.class);

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
        LOGGER.info("Setting up test");
        Mockito.when(spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty"))
                .thenReturn(new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false));

        Mockito.when(workflowRespository.findById(anyLong())).thenReturn(WorkflowTestUtils.WORKFLOW);
        Mockito.when(workflowAIService.whatIs(anyString(), anyString())).thenReturn(WorkflowTestUtils.WHAT_IS_A_MOOSE);
        Mockito.when(workflowAIService.writeAPoem(anyString(), anyString())).thenReturn(WorkflowTestUtils.MOOSE_POEM);
        Mockito.when(workflowAIService.addThisToThePoem(anyString(), anyString())).thenReturn(WorkflowTestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY);
        doNothing().when(workflowRespository).persist(Mockito.any(Workflow.class));
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("Tearing down test");
        Mockito.reset(spiritAnimalService, workflowAIService, workflowRespository);
    }

    @Test
    @Transactional
    public void testUpdatedPoem() {
        SpiritAnimalWorkflow updatedPoemWorkflow = workflowService.addToPoem(1L);
        assertEquals(updatedPoemWorkflow.updatedPoem().get(), WorkflowTestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY);
    }
}
