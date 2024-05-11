package io.arrogantprogrammer.spiritanimals.workflow.api;

import java.util.List;

public interface WorkflowService {
    WorkflowRecord assignSpiritAnimalFor(String name);

    WorkflowRecord whatIs(Long id);

    WorkflowRecord writeAPoem(final Long id);

    WorkflowRecord addToPoem(Long id);

    WorkflowRecord like(Long id);

    WorkflowRecord feedback(Long id, final String feedback);

    List<WorkflowRecord> allWorkflows();

}
