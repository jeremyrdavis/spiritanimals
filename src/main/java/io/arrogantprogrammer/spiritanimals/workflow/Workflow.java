package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Workflow {

    @Id @GeneratedValue
    Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "name")),
            @AttributeOverride( name = "animalName", column = @Column(name = "animalName")),
            @AttributeOverride( name = "id", column = @Column(name = "spirtitAnimaId")),
            @AttributeOverride( name = "liked", column = @Column(name = "liked"))
    })
    SpiritAnimalRecord spiritAnimalRecord;

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

    Workflow(SpiritAnimalRecord spiritAnimalRecord, String whatIs, String poem, String updatedPoem, boolean isLiked, String feedback) {
        this.spiritAnimalRecord = spiritAnimalRecord;
        this.whatIs = whatIs;
        this.poem = poem;
        this.updatedPoem = updatedPoem;
        this.isLiked = isLiked;
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "spiritAnimalAssignment=" + spiritAnimalRecord +
                ", whatIs='" + whatIs + '\'' +
                ", poem='" + poem + '\'' +
                ", updatedPoem='" + updatedPoem + '\'' +
                ", isLiked=" + isLiked +
                ", feedback='" + feedback + '\'' +
                ", id=" + id +
                '}';
    }

    public SpiritAnimalRecord getSpiritAnimalRecord() {
        return spiritAnimalRecord;
    }

    public void setSpiritAnimalRecord(SpiritAnimalRecord spiritAnimalRecord) {
        this.spiritAnimalRecord = spiritAnimalRecord;
    }

    public Long getId() {
        return id;
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
