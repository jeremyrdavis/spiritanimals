package io.arrogantprogrammer.spiritanimals.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Workflow extends PanacheEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    SpiritAnimalAssignment spiritAnimalAssignment;

    String whatIs;

    String poem;

    String updatedPoem;

    boolean isLiked;

    public Workflow() {
    }

    public SpiritAnimalAssignment getSpiritAnimalAssignment() {
        return spiritAnimalAssignment;
    }

    public void setSpiritAnimalAssignment(SpiritAnimalAssignment spiritAnimalAssignment) {
        this.spiritAnimalAssignment = spiritAnimalAssignment;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public String getUpdatedPoem() {
        return updatedPoem;
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
}
