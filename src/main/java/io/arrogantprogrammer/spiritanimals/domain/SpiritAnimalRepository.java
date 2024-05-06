package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.core.SpiritAnimal;
import io.arrogantprogrammer.spiritanimals.workflow.Workflow;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpiritAnimalRepository implements PanacheRepository<SpiritAnimal> {
    public Workflow findWorkflowById(final Long id) {
        return Workflow.findById(id);
    }

    public void persistWorkflow(Workflow workflow) {
        workflow.persist();
    }
}
