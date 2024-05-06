package io.arrogantprogrammer.spiritanimals.workflow;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class WorkflowServiceImplTest {

    static final Logger LOGGER = LoggerFactory.getLogger(WorkflowServiceImplTest.class);

    @Inject
    WorkflowServiceImpl workflowService;

    @Test
    public void testArticleAssignment() {
        LOGGER.info("Running testAssignSpiritAnimalFor");
        String result = workflowService.aOrAn("elephant");
        assertEquals("an", result);
        String aResult = workflowService.aOrAn("dog");
        assertEquals("a", aResult);
    }

}
