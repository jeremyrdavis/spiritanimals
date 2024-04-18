package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class SpiritAnimalServiceTest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceTest.class);

    @Test
    public void testGetAnimal() {
        LOGGER.info("Testing getAnimal");
    }
}
