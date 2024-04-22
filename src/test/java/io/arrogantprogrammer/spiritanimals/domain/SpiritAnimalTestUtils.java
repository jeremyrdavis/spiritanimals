package io.arrogantprogrammer.spiritanimals.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Set;

public class SpiritAnimalTestUtils {

    public static void addAnimals() {
        SpiritAnimal.addAnimals(Set.of(
                "Moose", "Bear", "Tiger", "Lion", "Elephant", "Giraffe", "Penguin", "Kangaroo", "Panda", "Polar Bear", "Koala", "Zebra", "Hippo", "Rhino",
                "Wolf", "Fox", "Rabbit", "Deer", "Squirrel", "Raccoon", "Otter", "Badger", "Skunk", "Weasel", "Beaver", "Porcupine", "Opossum", "Mouse", "Rat",
                "Bat", "Sparrow", "Robin", "Blue Jay", "Cardinal", "Hawk", "Eagle", "Owl", "Finch", "Starling", "Crow", "Dove", "Woodpecker", "Hummingbird",
                "Falcon", "Parrot", "Peacock", "Swan", "Goose", "Duck", "Chicken", "Turkey", "Pigeon"
        ));
    }
}
