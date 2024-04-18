package io.arrogantprogrammer.spiritanimals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class SpiritAnimalServiceTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceTest.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Test
    public void testGetAnimal() {
        LOGGER.info("Testing getAnimal");
        Animal animal = spiritAnimalService.getAnimal();
        assertNotNull(animal);
    }
}
