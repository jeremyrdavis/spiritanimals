package io.arrogantprogrammer.spiritanimals.core;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.domain.AnimalName;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class SpiritAnimal extends PanacheEntity {

    String name;
    String animalName;
    boolean liked;

    public SpiritAnimal() {
    }

    public SpiritAnimal(String name, String animalName) {
        this.name = name;
        this.animalName = animalName;
    }

    SpiritAnimal(String name, String animalName, boolean liked) {
        this.name = name;
        this.animalName = animalName;
        this.liked = liked;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAnimalName() {
        return animalName;
    }

    public boolean isLiked() {
        return liked;
    }

    void setName(String name) {
        this.name = name;
    }

    void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "SpiritAnimal{" +
                "name='" + name + '\'' +
                ", animalName='" + animalName + '\'' +
                ", liked=" + liked +
                ", id=" + id +
                '}';
    }

    // Business Logic
    //====================================================================================================
    @Transient
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimal.class);
    @Transient
    static Set<String> animalNames = new HashSet<String>();
    @Transient
    static Set<String> assignedAnimalNames = new HashSet<String>();

    /**
     * Add more names to the list of available animal names, filtering out animals that are already assigned
     * @param animalNames
     */
    @Transactional
    static SpiritAnimal assignSpiritAnimal(final String name) {
        if (animalNames.isEmpty()) {
            Stream<AnimalName> allAnimalNames = AnimalName.streamAll();
            animalNames.addAll(allAnimalNames
                    .map(p -> p.getName())
                    .collect(Collectors.toList()));
            LOGGER.info("Loaded {} animal names", animalNames.size());
        }
        return new SpiritAnimal(name, randomAnimalName());
    }

    static private String randomAnimalName() {
        return animalNames.stream().toList().get(new Random().nextInt(animalNames.size()));
    }
}
