package io.arrogantprogrammer.spiritanimals.api;

import io.arrogantprogrammer.spiritanimals.infrastructure.rest.domain.SpritAnimalAssignmentRecord;

public interface SpiritAnimalService {
    SpiritAnimalWorkflow assignSpiritAnimalFor(String name);

    SpiritAnimalWorkflow whatIs(Long id);

    SpiritAnimalWorkflow writeAPoem(final Long id);

    String addToPoem(String animalName, String poem);
}
