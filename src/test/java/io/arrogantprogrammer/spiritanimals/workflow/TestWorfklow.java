package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for Workflow Tests
 */
public class TestWorfklow {
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
}
