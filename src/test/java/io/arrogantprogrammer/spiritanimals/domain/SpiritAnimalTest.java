package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpiritAnimalTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalTest.class);

    /*
    * Test assigning a spirit animal
    */
    @Test
    public void testAssignSpiritAnimal() {
        LOGGER.info("Testing assign spirit animal");
        SpiritAnimalAssignment spiritAnimalAssignment = SpiritAnimal.assignSpiritAnimal("Luke");
        assertNotNull(spiritAnimalAssignment);
        assertEquals("Luke", spiritAnimalAssignment.getName());
        assertNotNull(spiritAnimalAssignment.getAnimal());
    }

    /*
    * Test that we can get a random animal
     */
    @Test
    public void testAnimalName() {
        LOGGER.info("Testing random animal");
        SpiritAnimal spiritAnimal = new SpiritAnimal();
        spiritAnimal.addAnimal("cat");
        Set<String> animalNames = spiritAnimal.getAnimalNames();
        assertNotNull(animalNames);
        assertEquals(1, animalNames.size());
        assertEquals("cat", animalNames.iterator().next());
    }

    /*
    * Test that we can get multiple animals at the same time
    */
    @Test
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
