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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@QuarkusTest
public class TestWorkflow02WhatIs {

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

        // Mock the spirit animal service functionality
        Mockito.when(spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty"))
                .thenReturn(new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false));

        Mockito.when(workflowRespository.findById(anyLong())).thenReturn(WorkflowTestUtils.WORKFLOW);
        Mockito.when(workflowAIService.whatIs(anyString(), anyString())).thenReturn(WorkflowTestUtils.WHAT_IS_A_MOOSE);
    }
    @Test
    @Transactional
    public void testWhatIs() {
        WorkflowRecord whatIsWorkflow = workflowService.whatIs(1L);
        assertNotNull(whatIsWorkflow.whatIs());
        assertEquals(whatIsWorkflow.whatIs().get(), WorkflowTestUtils.WHAT_IS_A_MOOSE);
        ArgumentCaptor<Workflow> argumentCaptor = ArgumentCaptor.forClass(Workflow.class);
        Mockito.verify(workflowRespository, Mockito.times(1)).persist(argumentCaptor.capture());
    }

}
