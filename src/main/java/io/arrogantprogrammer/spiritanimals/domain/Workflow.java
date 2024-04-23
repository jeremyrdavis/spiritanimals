package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Workflow extends PanacheEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    SpiritAnimal spiritAnimal;

    @Lob
    String whatIs;

    @Lob
    String poem;

    @Lob
    String updatedPoem;

    boolean isLiked;

    @Lob
    String feedback;

    public Workflow() {
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "spiritAnimalAssignment=" + spiritAnimal +
                ", whatIs='" + whatIs + '\'' +
                ", poem='" + poem + '\'' +
                ", updatedPoem='" + updatedPoem + '\'' +
                ", isLiked=" + isLiked +
                ", feedback='" + feedback + '\'' +
                ", id=" + id +
                '}';
    }

    public SpiritAnimal getSpiritAnimal() {
        return spiritAnimal;
    }

    public void setSpiritAnimal(SpiritAnimal spiritAnimalAssignment) {
        this.spiritAnimal = spiritAnimalAssignment;
    }

    public Optional<String> getWhatIs() {
        return Optional.ofNullable(whatIs);
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public Optional<String> getPoem() {
        return Optional.ofNullable(poem);
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public Optional<String> getUpdatedPoem() {
        return Optional.ofNullable(updatedPoem);
    }

    public void setUpdatedPoem(String updatedPoem) {
        this.updatedPoem = updatedPoem;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
