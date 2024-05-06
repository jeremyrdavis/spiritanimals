package io.arrogantprogrammer.spiritanimals.workflow.api;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalRecord;

import java.util.List;

public interface WorkflowService {
    SpiritAnimalWorkflow assignSpiritAnimalFor(String name);

    SpiritAnimalWorkflow whatIs(Long id);

    SpiritAnimalWorkflow writeAPoem(final Long id);

    SpiritAnimalWorkflow addToPoem(Long id);

    SpiritAnimalWorkflow like(Long id);

    SpiritAnimalWorkflow feedback(Long id, final String feedback);

}
