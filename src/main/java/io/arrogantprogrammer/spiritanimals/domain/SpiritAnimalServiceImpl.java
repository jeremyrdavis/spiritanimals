package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalWorkflow;
import io.arrogantprogrammer.spiritanimals.openai.OpenAIService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static io.arrogantprogrammer.spiritanimals.domain.SpiritAnimal.assignSpiritAnimal;

@ApplicationScoped
public class SpiritAnimalServiceImpl implements SpiritAnimalService {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalServiceImpl.class);

    static final List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @Inject
    OpenAIService openAIService;

    @Inject
    SpiritAnimalRepository spiritAnimalRepository;


    @Override
    public SpiritAnimalWorkflow assignSpiritAnimalFor(final String name) {
        LOGGER.info("Assigning spirit animal for {}", name);

        // assign the spirit animal
        SpiritAnimal spiritAnimal = assignSpiritAnimal(name);

        // persist the assignment
        spiritAnimalRepository.persist(spiritAnimal);
        Workflow spiritAnimalWorkflow = new Workflow();
        spiritAnimalWorkflow.setSpiritAnimal(spiritAnimal);
        spiritAnimalRepository.persistWorkflow(spiritAnimalWorkflow);

        LOGGER.debug("Assigned and persisted spirit animal for {}: {}", spiritAnimal.name, spiritAnimal.animalName);
        return new SpiritAnimalWorkflow(spiritAnimal.id, spiritAnimal.name, spiritAnimal.animalName);
    }

    @Override
    public SpiritAnimalWorkflow whatIs(final Long id) {
        Workflow spiritAnimalWorkflow = spiritAnimalRepository.findWorkflowById(id);
        String whatIsText = whatIs(spiritAnimalWorkflow.getSpiritAnimal().animalName); //openAIService.whatIs(aOrAn(spiritAnimalWorkflow.spiritAnimal()), spiritAnimalWorkflow.spiritAnimal());
        spiritAnimalWorkflow.setWhatIs(whatIsText);
        spiritAnimalRepository.persistWorkflow(spiritAnimalWorkflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(spiritAnimalWorkflow.id)
                .withName(spiritAnimalWorkflow.spiritAnimal.name)
                .withSpiritAnimal(spiritAnimalWorkflow.spiritAnimal.animalName)
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
        Workflow workflow = spiritAnimalRepository.findWorkflowById(id);
        LOGGER.debug("Write a poem about {} in the style of {}", workflow.spiritAnimal.animalName, poet);
        String poem = openAIService.writeAPoem(workflow.spiritAnimal.animalName, poet);
        workflow.setPoem(poem);
        spiritAnimalRepository.persistWorkflow(workflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.spiritAnimal.name)
                .withWhatIs(workflow.getWhatIs().orElse(""))
                .withSpiritAnimal(workflow.spiritAnimal.animalName)
                .withPoem(poem)
                .build();
    }

    String getAPoemFromOpenAI(String animalName, String poet) {
        return openAIService.writeAPoem(animalName, poet);
    }

    @Override
    public SpiritAnimalWorkflow addToPoem(final Long id) {
        String poeticAddition = POETICADDITION.addition();
        LOGGER.debug("Add {} the poem for workflow: {}", poeticAddition, id);
        Workflow workflow = spiritAnimalRepository.findWorkflowById(id);

        String updatedPoem = callLlmAddToPoem(poeticAddition, workflow.getPoem().orElse(POETICADDITION.addition()));
        workflow.setUpdatedPoem(updatedPoem);
        spiritAnimalRepository.persistWorkflow(workflow);

        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.spiritAnimal.name)
                .withSpiritAnimal(workflow.spiritAnimal.animalName)
                .withPoem(workflow.poem)
                .withUpdatedPoem(workflow.updatedPoem)
                .build();
    }

    String callLlmAddToPoem(String poeticAddition, String poem) {
        return openAIService.addThisToThePoem(poeticAddition, poem);
    }

    @Override
    public SpiritAnimalWorkflow like(final Long id) {
        LOGGER.debug("Liking spirit animal for id: {}", id);
        SpiritAnimal spiritAnimal = spiritAnimalRepository.findById(id);
        spiritAnimal.setLiked(true);
        Workflow workflow = spiritAnimalRepository.findWorkflowById(id);
        workflow.setLiked(true);
        spiritAnimalRepository.persistWorkflow(workflow);
        spiritAnimalRepository.persist(spiritAnimal);
        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.spiritAnimal.name)
                .withSpiritAnimal(workflow.spiritAnimal.animalName)
                .withWhatIs(workflow.getWhatIs().orElse(null))
                .withPoem(workflow.getPoem().orElse(null))
                .withUpdatedPoem(workflow.getUpdatedPoem().orElse(null))
                .isLiked()
                .build();
    }

    @Override
    public SpiritAnimalWorkflow feedback(final Long id, final String feedback) {
        LOGGER.debug("Feedback for spirit animal for id: {}", id);
        Workflow workflow = spiritAnimalRepository.findWorkflowById(id);
        workflow.setFeedback(feedback);
        spiritAnimalRepository.persistWorkflow(workflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.spiritAnimal.name)
                .withSpiritAnimal(workflow.spiritAnimal.animalName)
                .withPoem(workflow.poem)
                .withUpdatedPoem(workflow.updatedPoem)
                .withFeedback(workflow.feedback)
                .build();
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

    String aOrAn(String animalName) {
        char firstChar = Character.toLowerCase(animalName.charAt(0));
        if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u') {
            return "an";
        } else {
            return "a";
        }
    }
}
