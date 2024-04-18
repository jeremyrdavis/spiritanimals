package io.arrogantprogrammer.spiritanimals.domain;

import java.util.*;

public class SpiritAnimal {

    static Set<String> animalNames = new HashSet<String>();

    static void addAnimal(String animalName) {
        animalNames.add(animalName);
    }

    static void addAnimals(Collection<String> animalNames) {
        animalNames.forEach(SpiritAnimal::addAnimal);
    }

    static int remainingAnimalNames() {
        return animalNames.size();
    }
    static Set<String> getAnimalNames() {
        return animalNames;
    }

    static SpiritAnimalAssignmentResult assignSpiritAnimal(final String name) {

        if (animalNames.isEmpty()) {
            return new SpiritAnimalAssignmentResult(new SpiritAnimalAssignment(name, "Moose"), animalNames.size());
        }else{
            String animalName = randomAnimalName();
            animalNames.remove(animalName);
            return new SpiritAnimalAssignmentResult(new SpiritAnimalAssignment(name, animalName), animalNames.size());
        }
    }

    static private String randomAnimalName() {
        return animalNames.stream().toList().get(new Random().nextInt(animalNames.size()));
    }
}
