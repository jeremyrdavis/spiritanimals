package io.arrogantprogrammer.spiritanimals.openai;

import io.arrogantprogrammer.spiritanimals.workflow.WorkflowTestUtils;
import io.arrogantprogrammer.spiritanimals.workflow.WorkflowAIService;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@ApplicationScoped @Alternative @Priority(1)
public class MockWorkflowAIService implements WorkflowAIService {
    @Override
    public String whatIs(String article, String character) {
        return WorkflowTestUtils.WHAT_IS_A_MOOSE;
    }

    @Override
    public String writeAPoem(String animalName, String poet) {
        return WorkflowTestUtils.MOOSE_POEM;
    }

    @Override
    public String addThisToThePoem(String topic, String poem) {
        return WorkflowTestUtils.MOOSE_POEM_WITH_EDDIE_MURPHY;
    }

}
