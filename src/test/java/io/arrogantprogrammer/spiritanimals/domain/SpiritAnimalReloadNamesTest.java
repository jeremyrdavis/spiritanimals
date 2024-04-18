package io.arrogantprogrammer.spiritanimals.domain;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpiritAnimalReloadNamesTest {

    /*
     * Test that reloading the animal names works
     */
    @Test
    @Order(1)
    public void testReloadingAnimalNames() {

        NoMoreAnimalNamesException noMoreAnimalNamesException = null;
        try {
            SpiritAnimalAssignment spiritAnimalAssignment = SpiritAnimal.assignSpiritAnimal("Leia");
        } catch (NoMoreAnimalNamesException e) {
            noMoreAnimalNamesException = e;
        }
        assertNotNull(noMoreAnimalNamesException);

        SpiritAnimal.addAnimal("moose");
        try {
            SpiritAnimalAssignment spiritAnimalAssignment = SpiritAnimal.assignSpiritAnimal("Leia");
            assertNotNull(spiritAnimalAssignment);
            assertEquals("Leia", spiritAnimalAssignment.getName());
            assertEquals("moose", spiritAnimalAssignment.getAnimal());
        } catch (NoMoreAnimalNamesException e) {
            assertNull(e);
        }

    }
}
