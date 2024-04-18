package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class TestUniqueAnimalConstraints {

    static final Logger LOGGER = LoggerFactory.getLogger(TestUniqueAnimalConstraints.class);

    @Test
    public void testUniqueAnimal() {
        LOGGER.info("Testing unique animal");
        SpiritAnimal.addAnimal("cat");
        SpiritAnimalAssignmentResult spiritAnimalAssignmentResult = SpiritAnimal.assignSpiritAnimal("Luke");
        assertEquals(0, SpiritAnimal.remainingAnimalNames());
    }
}
