package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class TestWorkflowServiceAssignSpiritAnimal {

    @Inject
    WorkflowServiceImpl workflowService;

    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @InjectMock
    WorkflowRespository  workflowRespository;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(spiritAnimalService.assignSpiritAnimalFor(any(String.class))).thenReturn(new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false));
    }

    @AfterEach
    public void tearDown() {
        Log.infof("Tearing down test");
        ArgumentCaptor<Workflow> argumentCaptor = ArgumentCaptor.forClass(Workflow.class);
        Mockito.verify(workflowRespository, Mockito.times(1)).persist(argumentCaptor.capture());
        Mockito.reset(workflowRespository);
    }


    @Test
    @Transactional
    public void testAssignSpiritAnimal() {
        Log.infof("Testing assign spirit animal");
        // Test assigning a spirit animal
        WorkflowRecord workflow = workflowService.assignSpiritAnimalFor("Peppermint Patty");
        assertNotNull(workflow);
        assertEquals("Peppermint Patty", workflow.name());
        assertNotNull(workflow.spiritAnimal());
        assertNotNull(workflow.id());
        assertTrue(workflow.id() > 0);
        assertFalse(workflow.liked());
        assertTrue(workflow.whatIs().isEmpty());
        assertTrue(workflow.poem().isEmpty());
        assertTrue(workflow.updatedPoem().isEmpty());
        assertTrue(workflow.feedback().isEmpty());
    }

}
