package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.openai.OpenAIService;
import io.arrogantprogrammer.spiritanimals.openai.OpenAITestUtils;
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
public class SpiritAnimalServiceOpenAITest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceOpenAITest.class);

    @Inject
    SpiritAnimalServiceImpl spiritAnimalService;

    @InjectMock
    OpenAIService openAIService;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        Mockito.when(openAIService.whatIs("a","moose")).thenReturn(OpenAITestUtils.WHAT_IS_A_MOOSE);
        Mockito.when(openAIService.writeAPoem("a","moose")).thenReturn(OpenAITestUtils.MOOSE_POEM);
        Mockito.when(openAIService.writeAPoem(any(String.class), any(String.class))).thenReturn(OpenAITestUtils.MOOSE_POEM);
        Mockito.when(openAIService.addThisToThePoem(any(String.class),any(String.class))).thenReturn(OpenAITestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY);
    }

    @Test
    public void testWhatIs() {
        LOGGER.info("Testing whatIs");
        String whatIsResult = spiritAnimalService.whatIs("moose");
        assertNotNull(whatIsResult);
        assertEquals(OpenAITestUtils.WHAT_IS_A_MOOSE, whatIsResult);
    }

    @Test
    public void testWriteAPoem() {
        LOGGER.info("Testing writeAPoem");
        String poemResult = spiritAnimalService.getAPoemFromOpenAI("Jabba the Hut", "Sparrow");
        assertNotNull(poemResult);
        assertEquals(OpenAITestUtils.MOOSE_POEM, poemResult);
    }

    @Test
    public void testAddThisToThePoem() {
        LOGGER.info("Testing addThisToThePoem");
        String addThisToThePoemResult = spiritAnimalService.callLlmAddToPoem("moose", OpenAITestUtils.MOOSE_POEM);
        assertNotNull(addThisToThePoemResult);
        assertEquals(OpenAITestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY, addThisToThePoemResult);
    }
}
