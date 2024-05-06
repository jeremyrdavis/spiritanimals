package io.arrogantprogrammer.spiritanimals.workflow;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class TestWorkflowServiceArticleGeneration {
    @Inject
    WorkflowServiceImpl workflowService;
    @Test
    public void testArticle() {
        Log.infof("Testing article");
        String article = workflowService.aOrAn("Anteater");
        assertNotNull(article);
        assertEquals("an", article);
        String anotherArticle = workflowService.aOrAn("Bear");
        assertNotNull(anotherArticle);
        assertEquals("a", anotherArticle);
        String yetAnotherArticle = workflowService.aOrAn("Elephant");
        assertNotNull(yetAnotherArticle);
        assertEquals("an", yetAnotherArticle);
        String andAnotherArticle = workflowService.aOrAn("Iguana");
        assertEquals("an", andAnotherArticle);
        String sonOfAnotherArticle = workflowService.aOrAn("Ocelot");
        assertEquals("an", sonOfAnotherArticle);
        String returnOfTheSonOfAnotherArticle = workflowService.aOrAn("Uinta Ground Squirrel");
        assertEquals("an", returnOfTheSonOfAnotherArticle);
    }

}
