package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalWorkflow;
import io.arrogantprogrammer.spiritanimals.openai.OpenAITestUtils;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpiritAnimalServiceTest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceTest.class);
    private static final String FEEDBACK_TEXT = "I love my spirit animal!";

    @Inject
    SpiritAnimalServiceImpl spiritAnimalService;

    public void setUp() {
        LOGGER.info("Setting up test");
    }

    @Test @Transactional
    public void testAssignSpiritAnimal() {
        LOGGER.info("Testing assign spirit animal");
        // Test assigning a spirit animal
        SpiritAnimalWorkflow workflow = spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty");
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

    @Test
    @Transactional
    public void testWhatIs() {
        SpiritAnimalWorkflow workflow = spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty");
        assertNotNull(workflow);
        assertEquals("Peppermint Patty", workflow.name());
        SpiritAnimalWorkflow whatIsWorkflow = spiritAnimalService.whatIs(workflow.id());
        assertNotNull(whatIsWorkflow.whatIs());
        assertEquals(whatIsWorkflow.whatIs().get(), OpenAITestUtils.WHAT_IS_A_MOOSE);
    }

    @Test
    @Transactional
    public void testPoem() {
        SpiritAnimalWorkflow workflow = spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty");
        assertNotNull(workflow);
        assertEquals("Peppermint Patty", workflow.name());
        SpiritAnimalWorkflow poemWorkflow = spiritAnimalService.writeAPoem(workflow.id());
        assertNotNull(poemWorkflow.poem());
        assertEquals(poemWorkflow.poem().get(), OpenAITestUtils.MOOSE_POEM);
    }

    @Test
    @Transactional
    public void testUpdatedPoem() {
        SpiritAnimalWorkflow workflow = spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty");
        assertNotNull(workflow);
        assertEquals("Peppermint Patty", workflow.name());
        SpiritAnimalWorkflow updatedPoemWorkflow = spiritAnimalService.addToPoem(workflow.id());
        assertEquals(updatedPoemWorkflow.updatedPoem().get(), OpenAITestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY);

    }

    @Test
    @Transactional
    public void testLiked() {
        LOGGER.info("Testing liked");
        SpiritAnimalWorkflow workflow = spiritAnimalService.assignSpiritAnimalFor("Peppermint Patty");
        assertNotNull(workflow);
        assertEquals("Peppermint Patty", workflow.name());
        SpiritAnimalWorkflow likedWorkflow = spiritAnimalService.like(workflow.id());
        assertTrue(likedWorkflow.liked());
    }

    @Test
    @Transactional
    public void testFeedback() {
        LOGGER.info("Testing feedback");
        SpiritAnimalWorkflow workflow = spiritAnimalService.feedback(237L, FEEDBACK_TEXT);
        assertNotNull(workflow);
        assertEquals(FEEDBACK_TEXT, workflow.feedback().get());
    }

    @Test
    public void testArticle() {
        LOGGER.info("Testing article");
        String article = spiritAnimalService.aOrAn("Anteater");
        assertNotNull(article);
        assertEquals("an", article);
        String anotherArticle = spiritAnimalService.aOrAn("Bear");
        assertNotNull(anotherArticle);
        assertEquals("a", anotherArticle);
        String yetAnotherArticle = spiritAnimalService.aOrAn("Elephant");
        assertNotNull(yetAnotherArticle);
        assertEquals("an", yetAnotherArticle);
        String andAnotherArticle = spiritAnimalService.aOrAn("Iguana");
        assertEquals("an", andAnotherArticle);
        String sonOfAnotherArticle = spiritAnimalService.aOrAn("Ocelot");
        assertEquals("an", sonOfAnotherArticle);
        String returnOfTheSonOfAnotherArticle = spiritAnimalService.aOrAn("Uinta Ground Squirrel");
        assertEquals("an", returnOfTheSonOfAnotherArticle);
    }

}
