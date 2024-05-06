package io.arrogantprogrammer.spiritanimals.workflow;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WorkflowRespository implements PanacheRepository<Workflow> {
}
