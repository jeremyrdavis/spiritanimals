package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestUniqueAnimalConstraints {

    static final Logger LOGGER = LoggerFactory.getLogger(TestUniqueAnimalConstraints.class);

    @Test
    public void testUniqueAnimal() {
        LOGGER.info("Testing unique animal");
        clearOutAnimalNames();
        assertEquals(0, SpiritAnimal.remainingAnimalNames());
        SpiritAnimal.addAnimal("cat");
        assertEquals(1, SpiritAnimal.remainingAnimalNames());
        SpiritAnimal.addAnimal("cat");
        assertEquals(1, SpiritAnimal.remainingAnimalNames());
    }

    private void clearOutAnimalNames() {
        int currentNumberOfNames = SpiritAnimal.remainingAnimalNames();
        for(int i= 0; i < currentNumberOfNames; i++) {
            SpiritAnimal.assignSpiritAnimal(i + "");
        }
    }
}
