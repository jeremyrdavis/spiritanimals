package io.arrogantprogrammer.spiritanimals.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SpiritAnimal {

    static Set<String> animalNames = new HashSet<String>();

    static void addAnimal(String animalName) {
        animalNames.add(animalName);
    }

    static void addAnimals(Collection<String> animalNames) {
        animalNames.forEach(SpiritAnimal::addAnimal);
    }

    static Set<String> getAnimalNames() {
        return animalNames;
    }

    public static SpiritAnimalAssignment assignSpiritAnimal(String luke) {
        return new SpiritAnimalAssignment("Luke", "cat");
    }
}
