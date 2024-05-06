package io.arrogantprogrammer.spiritanimals.workflow;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class WorkflowServiceOpenAITest {
    static final Logger LOGGER = LoggerFactory.getLogger(WorkflowServiceOpenAITest.class);

    @Inject
    WorkflowServiceImpl spiritAnimalService;

    @InjectMock
    WorkflowAIService workflowAIService;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        Mockito.when(workflowAIService.whatIs("a","moose")).thenReturn(WorkflowTestUtils.WHAT_IS_A_MOOSE);
        Mockito.when(workflowAIService.writeAPoem("a","moose")).thenReturn(WorkflowTestUtils.MOOSE_POEM);
        Mockito.when(workflowAIService.writeAPoem(any(String.class), any(String.class))).thenReturn(WorkflowTestUtils.MOOSE_POEM);
        Mockito.when(workflowAIService.addThisToThePoem(any(String.class),any(String.class))).thenReturn(WorkflowTestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY);
    }

    @Test
    public void testWhatIs() {
        LOGGER.info("Testing whatIs");
        String whatIsResult = spiritAnimalService.whatIs("moose");
        assertNotNull(whatIsResult);
        assertEquals(WorkflowTestUtils.WHAT_IS_A_MOOSE, whatIsResult);
    }

    @Test
    public void testWriteAPoem() {
        LOGGER.info("Testing writeAPoem");
        String poemResult = spiritAnimalService.getAPoemFromOpenAI("Jabba the Hut", "Sparrow");
        assertNotNull(poemResult);
        assertEquals(WorkflowTestUtils.MOOSE_POEM, poemResult);
    }

    @Test
    public void testAddThisToThePoem() {
        LOGGER.info("Testing addThisToThePoem");
        String addThisToThePoemResult = spiritAnimalService.callLlmAddToPoem("moose", WorkflowTestUtils.MOOSE_POEM);
        assertNotNull(addThisToThePoemResult);
        assertEquals(WorkflowTestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY, addThisToThePoemResult);
    }
}