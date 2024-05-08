package io.arrogantprogrammer.spiritanimals.workflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WorkflowTest {

    @Test
    public void testToString() {
        Workflow workflow = new Workflow();
        assertNotNull(workflow.toString());
    }
}
