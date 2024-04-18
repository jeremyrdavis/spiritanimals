package io.arrogantprogrammer.spiritanimals;

import io.arrogantprogrammer.spiritanimals.domain.AnimalJson;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class SpiritSpiritAnimalServiceTestJson {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritSpiritAnimalServiceTestJson.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Test
    public void testGetAnimal() {
        LOGGER.info("Testing getAnimal");
        AnimalJson animalJson = spiritAnimalService.getAnimal();
        assertNotNull(animalJson);
    }
}
