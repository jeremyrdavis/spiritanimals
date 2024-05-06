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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class TestWorfklow01AssignSpiritAnimal {

    @Inject
    WorkflowServiceImpl workflowService;
    @InjectMock
    SpiritAnimalService spiritAnimalService;
    @InjectSpy
    WorkflowRespository workflowRespository;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty"))
                .thenReturn(new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false));
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
        ArgumentCaptor<Workflow> argumentCaptor = ArgumentCaptor.forClass(Workflow.class);
        Mockito.verify(workflowRespository, Mockito.times(1)).persist(argumentCaptor.capture());
    }

}
