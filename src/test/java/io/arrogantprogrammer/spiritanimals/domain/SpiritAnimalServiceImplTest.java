package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class SpiritAnimalServiceImplTest {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceImplTest.class);

    @Inject
    SpiritAnimalServiceImpl spiritAnimalService;

    @Test
    public void testArticleAssignment() {
        LOGGER.info("Running testAssignSpiritAnimalFor");
        String result = spiritAnimalService.aOrAn("elephant");
        assertEquals("an", result);
        String aResult = spiritAnimalService.aOrAn("dog");
        assertEquals("a", aResult);
    }
}
