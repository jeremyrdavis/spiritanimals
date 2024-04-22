package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class SpiritAnimalServiceMinimumNamesTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceMinimumNamesTest.class);

    @Inject
    SpiritAnimalServiceImpl spiritAnimalService;

    @Test @Transactional
    public void testMinimumNumberOfAnimals() {
        LOGGER.info("Testing getAnimal");
        int currentNumberOfNames = SpiritAnimal.remainingAnimalNames();
        spiritAnimalService.assignSpiritAnimalFor("R2D2");
        assertTrue(SpiritAnimal.remainingAnimalNames() >= 1);
    }
}
