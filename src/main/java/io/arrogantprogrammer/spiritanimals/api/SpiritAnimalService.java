package io.arrogantprogrammer.spiritanimals.api;

public interface SpiritAnimalService {
    SpiritAnimalWorkflow assignSpiritAnimalFor(String name);

    SpiritAnimalWorkflow whatIs(Long id);

    SpiritAnimalWorkflow writeAPoem(final Long id);

    SpiritAnimalWorkflow addToPoem(Long id);

    SpiritAnimalWorkflow like(Long id);

    SpiritAnimalWorkflow feedback(Long id, final String feedback);
}
