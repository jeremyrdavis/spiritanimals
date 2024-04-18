package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class SpiritAnimalServiceMinimumNamesTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceMinimumNamesTest.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Test
    public void testMinimumNumberOfAnimals() {
        LOGGER.info("Testing getAnimal");
        assertEquals(0, SpiritAnimal.remainingAnimalNames());
        spiritAnimalService.assignSpiritAnimalFor("R2D2");
        assertEquals(50, SpiritAnimal.remainingAnimalNames());
    }
}
