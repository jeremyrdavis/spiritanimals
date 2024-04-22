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
}
