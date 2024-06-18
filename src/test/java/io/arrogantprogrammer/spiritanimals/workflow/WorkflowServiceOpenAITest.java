package io.arrogantprogrammer.spiritanimals.workflow;

import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class WorkflowServiceOpenAITest {
    @Inject
    WorkflowServiceImpl spiritAnimalService;

    @InjectMock
    WorkflowAIService workflowAIService;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(workflowAIService.whatIs("a","moose")).thenReturn(WorkflowTestUtils.WHAT_IS_A_MOOSE);
        Mockito.when(workflowAIService.writeAPoem("a","moose")).thenReturn(WorkflowTestUtils.MOOSE_POEM);
        Mockito.when(workflowAIService.writeAPoem(any(String.class), any(String.class))).thenReturn(WorkflowTestUtils.MOOSE_POEM);
        Mockito.when(workflowAIService.addThisToThePoem(any(String.class),any(String.class))).thenReturn(WorkflowTestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY);
    }

    @Test
    public void testWhatIs() {
        Log.infof("Testing whatIs");
        String whatIsResult = spiritAnimalService.whatIs("moose");
        assertNotNull(whatIsResult);
        assertEquals(WorkflowTestUtils.WHAT_IS_A_MOOSE, whatIsResult);
    }

    @Test
    public void testWriteAPoem() {
        Log.infof("Testing writeAPoem");
        String poemResult = spiritAnimalService.getAPoemFromOpenAI("Jabba the Hut", "Sparrow");
        assertNotNull(poemResult);
        assertEquals(WorkflowTestUtils.MOOSE_POEM, poemResult);
    }

    @Test
    public void testAddThisToThePoem() {
        Log.infof("Testing addThisToThePoem");
        String addThisToThePoemResult = spiritAnimalService.callLlAddToPoem("moose", WorkflowTestUtils.MOOSE_POEM);
        assertNotNull(addThisToThePoemResult);
        assertEquals(WorkflowTestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY, addThisToThePoemResult);
    }
}
