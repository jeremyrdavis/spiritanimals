package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.annotation.RunOnVirtualThread;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Isolated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Isolated
public class SpiritAnimalTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalTest.class);

    /*
    * Test assigning a spirit animal
    */
    @Test
    @Order(1)
    public void testAssignSpiritAnimal() {

        int currentNumberOfNames = SpiritAnimal.remainingAnimalNames();
        int expectedNumberOfNames = currentNumberOfNames -1;

        LOGGER.info("Testing assign spirit animal");
        SpiritAnimalAssignmentResult spiritAnimalAssignmentResult = SpiritAnimal.assignSpiritAnimal("Luke");
        assertNotNull(spiritAnimalAssignmentResult);
        assertEquals("Luke", spiritAnimalAssignmentResult.spiritAnimalAssignment().getName());
        assertNotNull(spiritAnimalAssignmentResult.spiritAnimalAssignment().getAnimalName());
        assertEquals(expectedNumberOfNames, spiritAnimalAssignmentResult.remainingAnimalNames());
    }

    /*
    * Test that we can get multiple animals at the same time
    */
    @Test
    @Order(2)
    public void testAddingAnimalNames() {

        LOGGER.info("Testing adding animal names");

        int currentNumberOfNames = SpiritAnimal.remainingAnimalNames();
        int expectedNumberOfNames = currentNumberOfNames + 1;

        SpiritAnimal.addAnimals(new HashSet<>(Arrays.asList("dog")));
        Set<String> animalNames = SpiritAnimal.getAnimalNames();
        assertNotNull(animalNames);
        assertEquals(expectedNumberOfNames, animalNames.size());
        assertTrue(animalNames.contains("dog"));

        int expectedNumberOfNamesAfterAdding3 = expectedNumberOfNames + 3;
        SpiritAnimal.addAnimals(new HashSet<>(Arrays.asList("horse", "pig", "cow")));
        Set<String> updatedAnimalNames = SpiritAnimal.getAnimalNames();
        assertNotNull(updatedAnimalNames);
        assertEquals(expectedNumberOfNamesAfterAdding3, updatedAnimalNames.size(), "Should have 4 animals: dog, horse, pig, cow");
        assertTrue(updatedAnimalNames.contains("cow"));
        assertTrue(updatedAnimalNames.contains("horse"));
        assertTrue(updatedAnimalNames.contains("pig"));
    }

}
