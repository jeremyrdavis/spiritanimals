package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalRecord;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class SpiritAnimalServiceImplTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceImplTest.class);

    @Inject
    SpiritAnimalServiceImpl spiritAnimalService;

    @InjectMock
    SpiritAnimalRepository spiritAnimalRepository;

    @BeforeEach
    public void setUp() {
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
    public void testArticleAssignment() {
        LOGGER.info("Running testAssignSpiritAnimalFor");
        String result = spiritAnimalService.aOrAn("elephant");
        assertEquals("an", result);
        String aResult = spiritAnimalService.aOrAn("dog");
        assertEquals("a", aResult);
    }

    @Test
    public void testAllSpiritAnimals() {
        LOGGER.info("Running testAllSpiritAnimals");
        assertEquals(5, spiritAnimalService.allSpiritAnimals().size());
    }

    @Test
    public void testFindSpiritAnimalById() {
        LOGGER.info("Running testFindSpiritAnimalById");
        SpiritAnimalRecord spiritAnimal = spiritAnimalService.getSpiritAnimalById(1L);
        assertEquals("Kirk", spiritAnimal.name());
    }
}
