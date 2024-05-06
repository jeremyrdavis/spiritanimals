package io.arrogantprogrammer.spiritanimals.workflow.api;

public interface WorkflowService {
    WorkflowRecord assignSpiritAnimalFor(String name);

    WorkflowRecord whatIs(Long id);

    WorkflowRecord writeAPoem(final Long id);

    WorkflowRecord addToPoem(Long id);

    WorkflowRecord like(Long id);

    WorkflowRecord feedback(Long id, final String feedback);

}
