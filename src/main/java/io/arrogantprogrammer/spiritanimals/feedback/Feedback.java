package io.arrogantprogrammer.spiritanimals.feedback;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id @GeneratedValue
    Long id;
    Long workflowId;

    @Column(columnDefinition = "TEXT")
    String feedback;
    @Enumerated(EnumType.STRING)
    SENTIMENT sentiment;

    public Feedback() {
    }

    public Feedback(Long workflowId, String feedback) {
        this.workflowId = workflowId;
        this.feedback = feedback;
    }

    public Feedback(Long workflowId, String feedback, SENTIMENT sentiment) {
        this.workflowId = workflowId;
        this.feedback = feedback;
        this.sentiment = sentiment;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "workflowId=" + workflowId +
                ", feedback='" + feedback + '\'' +
                ", sentiment=" + sentiment +
                ", id=" + id +
                '}';
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public String getFeedback() {
        return feedback.toString();
    }

    public SENTIMENT getSentiment() {
        return sentiment;
    }
}
