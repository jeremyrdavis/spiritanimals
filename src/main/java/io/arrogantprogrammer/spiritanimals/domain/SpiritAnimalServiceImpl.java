package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalWorkflow;
import io.arrogantprogrammer.spiritanimals.infrastructure.rest.AnimalRestClient;
import io.arrogantprogrammer.spiritanimals.infrastructure.rest.domain.SpritAnimalAssignmentRecord;
import io.arrogantprogrammer.spiritanimals.openai.OpenAIService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.arrogantprogrammer.spiritanimals.domain.SpiritAnimal.assignSpiritAnimal;

@ApplicationScoped
public class SpiritAnimalServiceImpl implements SpiritAnimalService {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceImpl.class);

    static final List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @RestClient
    AnimalRestClient animalRestClient;

    @Inject
    OpenAIService openAIService;

    @Inject
    SpiritAnimalAssignmentRepository spiritAnimalAssignmentRepository;

    @Override
    public SpiritAnimalWorkflow assignSpiritAnimalFor(final String name) {
        LOGGER.info("Assigning spirit animal for {}", name);

        // if we are out of names get more
        if(SpiritAnimal.remainingAnimalNames() <= 0) {
            Set<String> animalNames = getMoreAnimalNames();
            SpiritAnimal.addAnimals(animalNames);
        }

        // assign the spirit animal
        SpiritAnimalAssignmentResult spiritAnimalAssignmentResult = assignSpiritAnimal(name);
        SpiritAnimalAssignment spiritAnimalAssignment = spiritAnimalAssignmentResult.spiritAnimalAssignment();

        // persist the assignment
        spiritAnimalAssignmentRepository.persist(spiritAnimalAssignment);

        LOGGER.debug("Assigned and persisted spirit animal for {}: {}", name, spiritAnimalAssignment.getAnimalName());
        return new SpiritAnimalWorkflow(spiritAnimalAssignmentResult.spiritAnimalAssignment().id, spiritAnimalAssignmentResult.spiritAnimalAssignment().name, spiritAnimalAssignmentResult.spiritAnimalAssignment().animalName);
    }

    @Override
    public SpiritAnimalWorkflow whatIs(SpiritAnimalWorkflow spiritAnimalWorkflow) {
        LOGGER.debug("What is {}", spiritAnimalWorkflow.spiritAnimal());
        String whatIsText = whatIs(spiritAnimalWorkflow.spiritAnimal()); //openAIService.whatIs(aOrAn(spiritAnimalWorkflow.spiritAnimal()), spiritAnimalWorkflow.spiritAnimal());
        return new SpiritAnimalWorkflow.Builder()
                .withId(spiritAnimalWorkflow.id())
                .withName(spiritAnimalWorkflow.name())
                .withSpiritAnimal(spiritAnimalWorkflow.spiritAnimal())
                .withWhatIs(whatIsText)
                .build();
    }

    String whatIs(String animalName) {
        LOGGER.debug("What is {}", animalName);
        return openAIService.whatIs(aOrAn(animalName), animalName);
    }

    @Override
    public SpiritAnimalWorkflow writeAPoem(Long id) {
        String poet = POET.randomPoet();
        Workflow workflow = spiritAnimalAssignmentRepository.findWorkflowById(id);
        LOGGER.debug("Write a poem about {} in the style of {}", workflow.spiritAnimalAssignment.animalName, poet);
        String poem = openAIService.writeAPoem(workflow.spiritAnimalAssignment.animalName, poet);
        workflow.setPoem(poem);
        spiritAnimalAssignmentRepository.persist(workflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.spiritAnimalAssignment.name)
                .withSpiritAnimal(workflow.spiritAnimalAssignment.animalName)
                .withPoem(poem)
                .build();
    }

    @Override
    public String addToPoem(String animalName, String poem) {
        String poeticAddition = POETICADDITION.addition();
        LOGGER.debug("Add {} the the following poem: {}", poeticAddition, poem);
        return openAIService.addThisToThePoem(poeticAddition, poem);
    }

    private Set<String> getMoreAnimalNames() {
        LOGGER.debug("Getting more animal names");

        Set<String> animalNames = new HashSet<>();
        animalRestClient.getAnimals(randomLetter()).forEach(animalJson -> {
            animalNames.add(animalJson.name());
        });
        LOGGER.debug("retrieved {} new animal names", animalNames.size());
        return animalNames;
    }

    String randomLetter() {
        return letters.get((int) (Math.random() * letters.size()));
    }

    String aOrAn(String animalName) {
        char firstChar = Character.toLowerCase(animalName.charAt(0));
        if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u') {
            return "an";
        } else {
            return "a";
        }
    }
}
