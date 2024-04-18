package io.arrogantprogrammer.spiritanimals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class SpiritAnimalServiceTestJson {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceTestJson.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Test
    public void testGetAnimal() {
        LOGGER.info("Testing getAnimal");
        AnimalJson animalJson = spiritAnimalService.getAnimal();
        assertNotNull(animalJson);
    }
}
