package io.arrogantprogrammer.spiritanimals.domain;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class SpiritAnimalReloadNamesTest {

    /*
     * Test that reloading the animal names works
     */
    @Test
    public void testReloadingAnimalNames() {

        SpiritAnimalAssignmentResult spiritAnimalAssignmentResult = SpiritAnimal.assignSpiritAnimal("Leia");
        assertNotNull(spiritAnimalAssignmentResult);
        assertEquals(0, spiritAnimalAssignmentResult.remainingAnimalNames());

        SpiritAnimal.addAnimals(new HashSet<String>(Arrays.asList("Bear", "Tiger", "Lion", "Elephant")));
        SpiritAnimalAssignmentResult anotherSpiritAnimalAssignmentResult = SpiritAnimal.assignSpiritAnimal("Leia");
        assertNotNull(anotherSpiritAnimalAssignmentResult);
        assertTrue(anotherSpiritAnimalAssignmentResult.remainingAnimalNames() > 0);
        assertEquals("Leia", anotherSpiritAnimalAssignmentResult.spiritAnimalAssignment().getName());
        assertTrue(Arrays.asList("Bear", "Tiger", "Lion", "Elephant").contains(anotherSpiritAnimalAssignmentResult.spiritAnimalAssignment().getAnimalName()));

    }
}
