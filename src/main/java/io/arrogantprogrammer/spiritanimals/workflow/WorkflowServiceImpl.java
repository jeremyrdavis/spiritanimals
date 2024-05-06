package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.domain.POET;
import io.arrogantprogrammer.spiritanimals.domain.POETICADDITION;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowService;
import io.arrogantprogrammer.spiritanimals.workflow.api.SpiritAnimalWorkflow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class WorkflowServiceImpl implements WorkflowService {

    static final Logger LOGGER = LoggerFactory.getLogger(WorkflowServiceImpl.class);

    static final List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Inject
    WorkflowRespository workflowRespository;

    @Inject
    WorkflowAIService workflowAIService;

    @Override
    public SpiritAnimalWorkflow assignSpiritAnimalFor(final String name) {
        LOGGER.info("Assigning spirit animal for {}", name);

        // assign the spirit animal
        SpiritAnimalRecord spiritAnimalRecord = spiritAnimalService.assignSpiritAnimalFor(name);

        // persist the assignment
        Workflow spiritAnimalWorkflow = new Workflow();
        spiritAnimalWorkflow.setSpiritAnimalRecord(spiritAnimalRecord);
        workflowRespository.persist(spiritAnimalWorkflow);

        LOGGER.debug("Assigned and persisted spirit animal for {}: {}", spiritAnimalRecord.name(), spiritAnimalRecord.animalName());
        return new SpiritAnimalWorkflow(spiritAnimalRecord.id(), spiritAnimalRecord.name(), spiritAnimalRecord.animalName());
    }

    @Override
    public SpiritAnimalWorkflow whatIs(final Long id) {
        Workflow spiritAnimalWorkflow = workflowRespository.findById(id);
        String whatIsText = whatIs(spiritAnimalWorkflow.getSpiritAnimalRecord().animalName()); //openAIService.whatIs(aOrAn(spiritAnimalWorkflow.animalName()), spiritAnimalWorkflow.animalName());
        spiritAnimalWorkflow.setWhatIs(whatIsText);
        workflowRespository.persist(spiritAnimalWorkflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(spiritAnimalWorkflow.id)
                .withName(spiritAnimalWorkflow.spiritAnimalRecord.name())
                .withSpiritAnimal(spiritAnimalWorkflow.spiritAnimalRecord.animalName())
                .withWhatIs(whatIsText)
                .build();
    }

    String whatIs(String animalName) {
        LOGGER.debug("What is {}", animalName);
        return workflowAIService.whatIs(aOrAn(animalName), animalName);
    }

    @Override
    public SpiritAnimalWorkflow writeAPoem(Long id) {
        String poet = POET.randomPoet();
        Workflow workflow = workflowRespository.findById(id);
        LOGGER.debug("Write a poem about {} in the style of {}", workflow.getSpiritAnimalRecord().animalName(), poet);
        String poem = workflowAIService.writeAPoem(workflow.getSpiritAnimalRecord().animalName(), poet);
        workflow.setPoem(poem);
        workflowRespository.persist(workflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.getSpiritAnimalRecord().name())
                .withWhatIs(workflow.getWhatIs().orElse(""))
                .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                .withPoem(poem)
                .build();
    }

    String getAPoemFromOpenAI(String animalName, String poet) {
        return workflowAIService.writeAPoem(animalName, poet);
    }

    @Override
    public SpiritAnimalWorkflow addToPoem(final Long id) {
        String poeticAddition = POETICADDITION.addition();
        LOGGER.debug("Add {} the poem for workflow: {}", poeticAddition, id);
        Workflow workflow = workflowRespository.findById(id);

        String updatedPoem = callLlmAddToPoem(poeticAddition, workflow.getPoem().orElse(POETICADDITION.addition()));
        workflow.setUpdatedPoem(updatedPoem);
        workflowRespository.persist(workflow);

        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.getSpiritAnimalRecord().name())
                .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                .withPoem(workflow.poem)
                .withUpdatedPoem(workflow.updatedPoem)
                .build();
    }

    String callLlmAddToPoem(String poeticAddition, String poem) {
        return workflowAIService.addThisToThePoem(poeticAddition, poem);
    }

    @Override
    public SpiritAnimalWorkflow like(final Long id) {
        LOGGER.debug("Liking spirit animal for id: {}", id);
        spiritAnimalService.like(id);
        Workflow workflow = workflowRespository.findById(id);
        workflow.setLiked(true);
        workflowRespository.persist(workflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.getSpiritAnimalRecord().name())
                .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                .withWhatIs(workflow.getWhatIs().orElse(null))
                .withPoem(workflow.getPoem().orElse(null))
                .withUpdatedPoem(workflow.getUpdatedPoem().orElse(null))
                .isLiked()
                .build();
    }

    @Override
    public SpiritAnimalWorkflow feedback(final Long id, final String feedback) {
        LOGGER.debug("Feedback for spirit animal for id: {}", id);
        Workflow workflow = workflowRespository.findById(id);
        workflow.setFeedback(feedback);
        workflowRespository.persist(workflow);
        return new SpiritAnimalWorkflow.Builder()
                .withId(workflow.id)
                .withName(workflow.getSpiritAnimalRecord().name())
                .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                .withPoem(workflow.poem)
                .withUpdatedPoem(workflow.updatedPoem)
                .withFeedback(workflow.feedback)
                .build();
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
