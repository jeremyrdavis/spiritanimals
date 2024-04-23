package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpiritAnimalTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalTest.class);

    /*
    * Test assigning a spirit animal
    */
    @Test @Transactional
    public void testAssignSpiritAnimal() {
        SpiritAnimal spiritAnimal = SpiritAnimal.assignSpiritAnimal("Sally");
        assertNotNull(spiritAnimal);
        assertEquals("Sally", spiritAnimal.getName());
        assertNotNull(spiritAnimal.getAnimalName());

        SpiritAnimal anotherSpiritAnimal = SpiritAnimal.assignSpiritAnimal("Linus");
        assertNotNull(anotherSpiritAnimal);
        assertEquals("Linus", anotherSpiritAnimal.getName());
        assertNotEquals(spiritAnimal.getAnimalName(), anotherSpiritAnimal.getAnimalName());
    }

}
