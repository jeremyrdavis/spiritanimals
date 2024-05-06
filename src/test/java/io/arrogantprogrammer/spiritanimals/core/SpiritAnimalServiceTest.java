package io.arrogantprogrammer.spiritanimals.core;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalRepository;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class SpiritAnimalServiceTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceTest.class);

    @Inject
    SpiritAnimalServiceImpl spiritAnimalService;

    @InjectMock
    SpiritAnimalRepository spiritAnimalRepository;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(spiritAnimalRepository.listAll()).thenReturn(Stream.of(
                new SpiritAnimal("Kirk", "elephant", true),
                new SpiritAnimal("Spock", "dog", true),
                new SpiritAnimal("Uhuru", "cat", true),
                new SpiritAnimal("Scotty", "lion", true),
                new SpiritAnimal("Bones", "tiger", false)
        ).collect(Collectors.toList()));

        Mockito.when(spiritAnimalRepository.findById(any(Long.class))).thenReturn(new SpiritAnimal("Kirk", "elephant", true));
    }

    @Test
    public void testAllSpiritAnimals() {
        Log.infof("Running testAllSpiritAnimals");
        assertEquals(5, spiritAnimalService.allSpiritAnimals().size());
    }

    @Test
    public void testFindSpiritAnimalById() {
        Log.infof("Running testFindSpiritAnimalById");
        SpiritAnimalRecord spiritAnimal = spiritAnimalService.getSpiritAnimalById(1L);
        assertEquals("Kirk", spiritAnimal.name());
    }

}
