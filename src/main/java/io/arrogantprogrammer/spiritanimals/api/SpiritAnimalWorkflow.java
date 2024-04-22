package io.arrogantprogrammer.spiritanimals.api;

import java.util.Optional;

/**
 * Represents a SpiritAnimalWorkflow, the steps for assigning a spirit animal to a person.
 *
 * @param id
 * @param name
 * @param spiritAnimal
 * @param accepted
 * @param whatIs
 * @param poem
 * @param updatedPoem
 * @param feedback
 */
public record SpiritAnimalWorkflow(
        Long id, String name, String spiritAnimal, boolean accepted,
       Optional<String> whatIs, Optional<String> poem, Optional<String> updatedPoem, Optional<String> feedback){

    /**
     * Convenience constructor for creating a new SpiritAnimalWorkflow
     * @param name
     * @param id
     * @param spiritAnimal
     */
    public SpiritAnimalWorkflow(Long id, String name, String spiritAnimal) {
        this(id, name, spiritAnimal, false, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    public SpiritAnimalWorkflow(Long id, String name, String spiritAnimal, String whatIsText) {
        this(id, name, spiritAnimal, false, Optional.of(whatIsText), Optional.empty(), Optional.empty(), Optional.empty());
    }

    public static class Builder {
        Long id;
        String name;
        String spiritAnimal;
        boolean accepted;
        Optional<String> whatIs;
        Optional<String> poem;
        Optional<String> updatedPoem;
        Optional<String> feedback;

        public Builder(){

        }
        public Builder(String name, String spiritAnimal) {
            this.name = name;
            this.spiritAnimal = spiritAnimal;
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

        public Builder withAccepted(boolean accepted) {
            this.accepted = accepted;
            return this;
        }

        public Builder withWhatIs(String whatIs) {
            this.whatIs = Optional.of(whatIs);
            return this;
        }

        public Builder withPoem(Optional<String> poem) {
            this.poem = poem;
            return this;
        }

        public Builder withUpdatedPoem(Optional<String> updatedPoem) {
            this.updatedPoem = updatedPoem;
            return this;
        }

        public SpiritAnimalWorkflow build() {
            return new SpiritAnimalWorkflow(id, name, spiritAnimal, accepted, whatIs, poem, updatedPoem, feedback);
        }
    }

}
