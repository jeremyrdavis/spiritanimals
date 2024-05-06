package io.arrogantprogrammer.spiritanimals.core.api;

import java.util.List;

public interface SpiritAnimalService {

    public SpiritAnimalRecord assignSpiritAnimalFor(String name);

    void like(Long id);

    List<SpiritAnimalRecord> allSpiritAnimals();

    SpiritAnimalRecord getSpiritAnimalById(Long id);

}
