package io.arrogantprogrammer.spiritanimals.workflow.api;

import java.util.Optional;

/**
 * Represents a SpiritAnimalWorkflow, the steps for assigning a spirit animal to a person.
 *
 * @param id
 * @param name
 * @param spiritAnimal
 * @param liked
 * @param whatIs
 * @param poem
 * @param updatedPoem
 * @param feedback
 */
public record WorkflowRecord(
        Long id, String name, String spiritAnimal, boolean liked,
       Optional<String> whatIs, Optional<String> poem, Optional<String> updatedPoem, Optional<String> feedback){

    /**
     * Convenience constructor for creating a new SpiritAnimalWorkflow with the basic information.
     * @param name
     * @param id
     * @param spiritAnimal
     */
    public WorkflowRecord(Long id, String name, String spiritAnimal) {
        this(id, name, spiritAnimal, false, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    /**
     * Builder for creating a new SpiritAnimalWorkflow.
     */
    public static class Builder {
        Long id;
        String name;
        String spiritAnimal;
        boolean liked;
        Optional<String> whatIs;
        Optional<String> poem;
        Optional<String> updatedPoem;
        Optional<String> feedback;

        public Builder(){

        }
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSpiritAnimal(String spiritAnimal) {
            this.spiritAnimal = spiritAnimal;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withWhatIs(String whatIs) {
            this.whatIs = Optional.ofNullable(whatIs);
            return this;
        }

        public Builder withPoem(String poem) {
            this.poem = Optional.ofNullable(poem);
            return this;
        }

        public Builder withUpdatedPoem(String updatedPoem) {
            this.updatedPoem = Optional.ofNullable(updatedPoem);
            return this;
        }

        public Builder liked(boolean liked) {
            this.liked = liked;
            return this;
        }

        public Builder isLiked(){
            this.liked = true;
            return this;
        }

        public Builder notLiked(){
            this.liked = false;
            return this;
        }

        public Builder withFeedback(String feedback) {
            this.feedback = Optional.ofNullable(feedback);
            return this;
        }

        public WorkflowRecord build() {
            return new WorkflowRecord(id, name, spiritAnimal, liked, whatIs, poem, updatedPoem, feedback);
        }

    }

}
