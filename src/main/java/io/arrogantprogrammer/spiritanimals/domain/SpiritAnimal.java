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

    static SpiritAnimalAssignment assignSpiritAnimal(final String name) throws NoMoreAnimalNamesException {
        if(animalNames.isEmpty()) {
            throw new NoMoreAnimalNamesException("No animals available");
        }
        int num = (int) (Math.random() * animalNames.size());
        String animalName = randomAnimalName();
        return new SpiritAnimalAssignment(name, animalName);
    }

    static private String randomAnimalName() {
        int num = (int) (Math.random() * animalNames.size());
        for(String animalName: animalNames) if (--num < 0) return animalName;
        return null;
    }
}
