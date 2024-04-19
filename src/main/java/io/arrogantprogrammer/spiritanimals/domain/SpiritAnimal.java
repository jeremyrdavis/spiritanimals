package io.arrogantprogrammer.spiritanimals.domain;

import java.util.*;

public class SpiritAnimal {

    static Set<String> animalNames = new HashSet<String>();

    static Set<String> assignedAnimalNames = new HashSet<String>();

    static void addAnimal(String animalName) {
        animalNames.add(animalName);
    }

    /**
     * Add more names to the list of available animal names, filtering out animals that are already assigned
     * @param animalNames
     */
    static void addAnimals(Collection<String> animalNames) {
        animalNames.forEach(name -> {
            if(!assignedAnimalNames.contains(name))
                SpiritAnimal.addAnimal(name);
        });
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
            assignedAnimalNames.add(animalName);
            return new SpiritAnimalAssignmentResult(new SpiritAnimalAssignment(name, animalName), animalNames.size());
        }
    }

    static private String randomAnimalName() {
        return animalNames.stream().toList().get(new Random().nextInt(animalNames.size()));
    }
}
