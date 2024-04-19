package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.openai.OpenAITestUtils;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class SpiritAnimalServiceOpenAITest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceOpenAITest.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

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
        String writeAPoemResult = spiritAnimalService.writeAPoem("moose");
        assertNotNull(writeAPoemResult);
        assertEquals(OpenAITestUtils.MOOSE_POEM, writeAPoemResult);
    }

    @Test
    public void testAddThisToThePoem() {
        LOGGER.info("Testing addThisToThePoem");
        String addThisToThePoemResult = spiritAnimalService.addToPoem("moose", OpenAITestUtils.MOOSE_POEM);
        assertNotNull(addThisToThePoemResult);
        assertEquals(OpenAITestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY, addThisToThePoemResult);
    }
}
