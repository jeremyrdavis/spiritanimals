package io.arrogantprogrammer.spiritanimals.api;

import io.arrogantprogrammer.spiritanimals.infrastructure.rest.domain.SpritAnimalAssignmentRecord;

public interface SpiritAnimalService {
    SpiritAnimalWorkflow assignSpiritAnimalFor(String name);

    String whatIs(String animalName);

    String writeAPoem(String animalName);

    String addToPoem(String animalName, String poem);
}
