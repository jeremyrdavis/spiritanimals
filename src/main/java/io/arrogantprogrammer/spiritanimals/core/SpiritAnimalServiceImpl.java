package io.arrogantprogrammer.spiritanimals.core;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class SpiritAnimalServiceImpl implements SpiritAnimalService {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceImpl.class);

    @Inject
    SpiritAnimalRepository spiritAnimalRepository;

    @Override @Transactional
    public SpiritAnimalRecord assignSpiritAnimalFor(String name) {
        SpiritAnimal spiritAnimal = SpiritAnimal.assignSpiritAnimal(name);
        spiritAnimalRepository.persist(spiritAnimal);
        return new SpiritAnimalRecord(spiritAnimal.getId(), spiritAnimal.getName(), spiritAnimal.getAnimalName(), spiritAnimal.isLiked());
    }

    @Override
    public void like(Long id) {

    }

    @Override
    public List<SpiritAnimalRecord> allSpiritAnimals() {
        return spiritAnimalRepository.listAll().stream().map(spiritAnimal -> new SpiritAnimalRecord(spiritAnimal.id, spiritAnimal.name, spiritAnimal.animalName, spiritAnimal.liked)).toList();
    }

    @Override
    public SpiritAnimalRecord getSpiritAnimalById(final Long id) {
        SpiritAnimal spiritAnimal = spiritAnimalRepository.findById(id);
        return new SpiritAnimalRecord(spiritAnimal.id, spiritAnimal.name, spiritAnimal.animalName, spiritAnimal.liked);
    }


}
