package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class SpiritAnimalAssignment extends PanacheEntity {

    String name;

    String animalName;

    public SpiritAnimalAssignment() {
    }

    public SpiritAnimalAssignment(String name, String animalName) {
        this.name = name;
        this.animalName = animalName;
    }

    public String getName() {
        return name;
    }

    public String getAnimal() {
        return animalName;
    }

    public String getAnimalName() {
        return animalName;
    }
}
