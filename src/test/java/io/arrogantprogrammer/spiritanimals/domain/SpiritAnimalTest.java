package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpiritAnimalTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalTest.class);

    /*
    * Test assigning a spirit animal
    */
    @Test
    @Order(1)
    public void testAssignSpiritAnimal() {
        LOGGER.info("Testing assign spirit animal");
        SpiritAnimal.addAnimal("cat");
        SpiritAnimalAssignmentResult spiritAnimalAssignmentResult = SpiritAnimal.assignSpiritAnimal("Luke");
        assertNotNull(spiritAnimalAssignmentResult);
        assertEquals("Luke", spiritAnimalAssignmentResult.spiritAnimalAssignment().getName());
        assertNotNull(spiritAnimalAssignmentResult.spiritAnimalAssignment().getAnimalName());
        assertEquals(0, spiritAnimalAssignmentResult.remainingAnimalNames());
    }

    /*
    * Test that we can get multiple animals at the same time
    */
    @Test
    @Order(2)
    public void testAnimalNames() {
        LOGGER.info("Testing random animal");
        SpiritAnimal.addAnimals(new HashSet<>(Arrays.asList("cat")));
        Set<String> animalNames = SpiritAnimal.getAnimalNames();
        assertNotNull(animalNames);
        assertEquals(1, animalNames.size());
        assertEquals("cat", animalNames.iterator().next());

        SpiritAnimal.addAnimals(new HashSet<>(Arrays.asList("dog", "pig", "cat")));
        Set<String> updatedAnimalNames = SpiritAnimal.getAnimalNames();
        assertNotNull(updatedAnimalNames);
        assertEquals(3, updatedAnimalNames.size());
        assertTrue(updatedAnimalNames.contains("cat"));
        assertTrue(updatedAnimalNames.contains("dog"));
        assertTrue(updatedAnimalNames.contains("pig"));
    }

}
