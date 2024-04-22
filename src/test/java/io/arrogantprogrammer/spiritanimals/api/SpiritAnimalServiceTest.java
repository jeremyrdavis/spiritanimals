package io.arrogantprogrammer.spiritanimals.api;

import io.arrogantprogrammer.spiritanimals.infrastructure.rest.AnimalRestClient;
import io.arrogantprogrammer.spiritanimals.infrastructure.rest.domain.AnimalJson;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpiritAnimalServiceTest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceTest.class);
    private static final String FEEDBACK_TEXT = "I love my spirit animal!";

    @Inject
    SpiritAnimalService spiritAnimalService;
    @InjectMock
    @RestClient
    AnimalRestClient animalRestClient;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        Mockito.when(animalRestClient.getAnimals("a")).thenReturn(TestUtils.getAnimals());
    }

    @Test
    public void testTestUtils() {
        LOGGER.info("Testing test utils");
        List<AnimalJson> result = TestUtils.getAnimals();
        assertNotNull(result);
        assertTrue(result.size() >= 10);
    }

    @Test @Transactional
    public void testAssignSpiritAnimal() {
        LOGGER.info("Testing assign spirit animal");
        // Test assigning a spirit animal
        SpiritAnimalWorkflow workflow = spiritAnimalService.assignSpiritAnimalFor("Chewie");
        assertNotNull(workflow);
        assertEquals("Chewie", workflow.name());
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
    public void testFeedback() {
        LOGGER.info("Testing feedback");
        SpiritAnimalWorkflow workflow = spiritAnimalService.feedback(237L, FEEDBACK_TEXT);
        assertNotNull(workflow);
        assertEquals(FEEDBACK_TEXT, workflow.feedback().get());
    }
}
