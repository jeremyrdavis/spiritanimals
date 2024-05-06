package io.arrogantprogrammer.spiritanimals.core.api;

import jakarta.persistence.Embeddable;

@Embeddable
public record SpiritAnimalRecord(Long id, String name, String animalName, boolean liked) {
}
