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
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TestWorkflowServiceLike {

    static final Logger LOGGER = LoggerFactory.getLogger(TestWorkflowServiceLike.class);

    @InjectSpy
    SpiritAnimalService spiritAnimalService;

    @InjectMock
    WorkflowRespository workflowRespository;

    @Inject
    WorkflowServiceImpl workflowService;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        Mockito.when(workflowRespository.findById(any(Long.class))).thenReturn(new Workflow(
                new SpiritAnimalRecord(1L, "Peppermint Patty", "Moose", false),
                WorkflowTestUtils.WHAT_IS_A_MOOSE,
                WorkflowTestUtils.MOOSE_POEM,
                null,
                false,
                null
        ));
        ArgumentCaptor<Workflow> argumentCaptor = ArgumentCaptor.forClass(Workflow.class);
        doNothing().when(workflowRespository).persist(argumentCaptor.capture());
    }

    @AfterEach
    public void tearDown() {
        LOGGER.info("Tearing down test");
        Mockito.verify(spiritAnimalService, Mockito.times(1)).like(any(Long.class));
        Mockito.reset(spiritAnimalService);
        Mockito.reset(workflowRespository);
    }

    @Test
    @Transactional
    public void testLike() {
        LOGGER.info("Testing liked");
        SpiritAnimalWorkflow likedWorkflow = workflowService.like(2L);
        assertTrue(likedWorkflow.liked());
    }


}
