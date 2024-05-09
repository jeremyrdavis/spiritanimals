package io.arrogantprogrammer.spiritanimals.workflow;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class AnimalApiToolTest {

    @Inject
    AnimalApiTool animalApiTool;

    @Test
    public void testGetAnimalDetails() {
        final AnimalRecord animalRecord = animalApiTool.getAnimalDetails("cheetah");
        assertNotNull(animalRecord);
        assertTrue(animalRecord.name().equalsIgnoreCase("cheetah"));
    }


}
