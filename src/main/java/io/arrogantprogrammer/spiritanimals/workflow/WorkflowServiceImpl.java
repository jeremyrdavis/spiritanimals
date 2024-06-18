package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.domain.POET;
import io.arrogantprogrammer.spiritanimals.domain.POETICADDITION;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class WorkflowServiceImpl implements WorkflowService {

    private static final List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Inject
    WorkflowRespository workflowRespository;

    @Inject
    WorkflowAIService workflowAIService;

    @Inject
    FeedbackService feedbackService;

    @Override
    public WorkflowRecord assignSpiritAnimalFor(final String name) {
        Log.debugf("Assigning spirit animal for %s", name);

        // assign the spirit animal
        SpiritAnimalRecord spiritAnimalRecord = spiritAnimalService.assignSpiritAnimalFor(name);

        // persist the assignment
        Workflow spiritAnimalWorkflow = new Workflow();
        spiritAnimalWorkflow.setSpiritAnimalRecord(spiritAnimalRecord);
        workflowRespository.persist(spiritAnimalWorkflow);

         Log.debugf("Assigned and persisted spirit animal for %s: %s", spiritAnimalRecord.name(), spiritAnimalRecord.animalName());
        return new WorkflowRecord(spiritAnimalRecord.id(), spiritAnimalRecord.name(), spiritAnimalRecord.animalName());
    }

    @Override
    public WorkflowRecord whatIs(final Long id) {
        Workflow spiritAnimalWorkflow = workflowRespository.findById(id);
        String whatIsText = whatIs(spiritAnimalWorkflow.getSpiritAnimalRecord().animalName()); //openAIService.whatIs(aOrAn(spiritAnimalWorkflow.animalName()), spiritAnimalWorkflow.animalName());
        spiritAnimalWorkflow.setWhatIs(whatIsText);
        workflowRespository.persist(spiritAnimalWorkflow);
        return new WorkflowRecord.Builder()
                .withId(spiritAnimalWorkflow.id)
                .withName(spiritAnimalWorkflow.spiritAnimalRecord.name())
                .withSpiritAnimal(spiritAnimalWorkflow.spiritAnimalRecord.animalName())
                .withWhatIs(whatIsText)
                .build();
    }

    String whatIs(String animalName) {
         Log.debugf("What is %s", animalName);
        return workflowAIService.whatIs(aOrAn(animalName), animalName);
    }

    @Override
    public WorkflowRecord writeAPoem(Long id) {
        String poet = POET.randomPoet();
        Workflow workflow = workflowRespository.findById(id);
         Log.debugf("Write a poem about %s in the style of %s", workflow.getSpiritAnimalRecord().animalName(), poet);
        String poem = workflowAIService.writeAPoem(workflow.getSpiritAnimalRecord().animalName(), poet);
        workflow.setPoem(poem);
        workflowRespository.persist(workflow);
        return new WorkflowRecord.Builder()
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
    public WorkflowRecord addToPoem(final Long id) {
        POETICADDITION poeticAddition = POETICADDITION.randomAddition();
        Log.debugf("Add %s the poem for workflow: %s", poeticAddition, id);
        Workflow workflow = workflowRespository.findById(id);
        workflow.setPoeticAddition(poeticAddition.addition());

        String updatedPoem = callLlAddToPoem(poeticAddition.addition(), workflow.getPoem().get());
        workflow.setUpdatedPoem(updatedPoem);
        Log.debugf("Updated poem: %s", updatedPoem);
        workflowRespository.persist(workflow);

        return new WorkflowRecord.Builder()
                .withId(workflow.id)
                .withName(workflow.getSpiritAnimalRecord().name())
                .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                .withPoem(workflow.poem)
                .withUpdatedPoem(workflow.updatedPoem)
                .withPoeticAddition(workflow.poeticAddition)
                .build();
    }

    String callLlAddToPoem(String poeticAddition, String poem) {
        return workflowAIService.addThisToThePoem(poeticAddition, poem);
    }

    @Override
    public WorkflowRecord like(final Long id) {
        Log.debugf("Liking spirit animal for id: %s", id);
        spiritAnimalService.like(id);
        Workflow workflow = workflowRespository.findById(id);
        workflow.setLiked(true);
        workflowRespository.persist(workflow);
        return new WorkflowRecord.Builder()
                .withId(workflow.id)
                .withName(workflow.getSpiritAnimalRecord().name())
                .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                .withWhatIs(workflow.getWhatIs().orElse(null))
                .withPoem(workflow.getPoem().orElse(null))
                .withUpdatedPoem(workflow.getUpdatedPoem().orElse(null))
                .liked(workflow.isLiked())
                .build();
    }

    @Override
    public WorkflowRecord feedback(final Long id, final String feedback) {
        Log.debugf("Feedback for spirit animal for id: %s", id);
        Workflow workflow = workflowRespository.findById(id);
        workflow.setFeedback(feedback);
        workflowRespository.persist(workflow);
        feedbackService.processFeedback(new FeedbackRecord(workflow.getId(), workflow.getFeedback()));
        return new WorkflowRecord.Builder()
                .withId(workflow.getId())
                .withName(workflow.getSpiritAnimalRecord().name())
                .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                .withPoem(workflow.poem)
                .withUpdatedPoem(workflow.updatedPoem)
                .withFeedback(workflow.feedback)
                .liked(workflow.isLiked())
                .build();
    }

    @Override
    public List<WorkflowRecord> allWorkflows() {
        return workflowRespository.streamAll().map(workflow -> {
            return new WorkflowRecord.Builder()
                    .withId(workflow.getId())
                    .withName(workflow.getSpiritAnimalRecord().name())
                    .withSpiritAnimal(workflow.getSpiritAnimalRecord().animalName())
                    .withPoem(workflow.poem)
                    .withUpdatedPoem(workflow.updatedPoem)
                    .withFeedback(workflow.feedback)
                    .liked(workflow.isLiked())
                    .build();
        }).collect(Collectors.toList());
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
