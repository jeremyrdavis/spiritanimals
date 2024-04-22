package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalWorkflow;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpiritAnimalAssignmentRepository implements PanacheRepository<SpiritAnimalAssignment> {
    public Workflow findWorkflowById(final Long id) {
        return Workflow.findById(id);
    }

    public void persist(Workflow workflow) {
        workflow.persist();
    }
}
