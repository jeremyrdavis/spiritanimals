package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.infrastructure.rest.AnimalRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.arrogantprogrammer.spiritanimals.domain.SpiritAnimal.assignSpiritAnimal;

@ApplicationScoped
public class SpiritAnimalService {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalService.class);

    static final List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @RestClient
    AnimalRestClient animalRestClient;

    @Inject
    SpiritAnimalAssignmentRepository spiritAnimalAssignmentRepository;

    public SpritAnimalAssignmentRecord assignSpiritAnimalFor(final String name) {
        LOGGER.info("Assigning spirit animal for {}", name);

        // if we are out of names get more
        if(SpiritAnimal.remainingAnimalNames() < 50) {
            Set<String> animalNames = getMoreAnimalNames();
            SpiritAnimal.addAnimals(animalNames);
        }

        // assign the spirit animal
        SpiritAnimalAssignmentResult spiritAnimalAssignmentResult = assignSpiritAnimal(name);
        SpiritAnimalAssignment spiritAnimalAssignment = spiritAnimalAssignmentResult.spiritAnimalAssignment();
        spiritAnimalAssignmentRepository.persist(spiritAnimalAssignment);
        LOGGER.debug("Assigned and persisted spirit animal for {}: {}", name, spiritAnimalAssignment.getAnimalName());
        return new SpritAnimalAssignmentRecord(spiritAnimalAssignmentResult.spiritAnimalAssignment().id, spiritAnimalAssignmentResult.spiritAnimalAssignment().name, spiritAnimalAssignmentResult.spiritAnimalAssignment().animalName);
    }

    private Set<String> getMoreAnimalNames() {

        Set<String> animalNames = new HashSet<>();

        while(SpiritAnimal.remainingAnimalNames() < 50) {
            animalRestClient.getAnimals(randomLetter()).forEach(animalJson -> {
                animalNames.add(animalJson.name());
            });
        }
        LOGGER.debug("retrieved {} new animal names", animalNames.size());
        return animalNames;
    }

    private String randomLetter() {
        return letters.get((int) (Math.random() * letters.size()));
    }

    public AnimalJson getAnimal() {

        List<AnimalJson> animalJsonList = animalRestClient.getAnimals(randomLetter());
        LOGGER.debug("retrieved {} animals", animalJsonList.size());

        if(animalJsonList.size() > 0) {
            return animalJsonList.get(0);
        } else {
            return null;
        }
    }
}
