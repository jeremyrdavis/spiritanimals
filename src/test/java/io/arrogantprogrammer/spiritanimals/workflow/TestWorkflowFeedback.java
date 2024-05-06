package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.arrogantprogrammer.spiritanimals.workflow.WorkflowTestUtils.FEEDBACK_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

@QuarkusTest
public class TestWorkflowFeedback {

    @Inject
    WorkflowServiceImpl workflowService;
    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @InjectMock
    WorkflowRespository workflowRespository;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Workflow workflow = WorkflowTestUtils.WORKFLOW;
        workflow.id = 1L;
        Mockito.when(workflowRespository.findById(anyLong())).thenReturn(workflow);
        doNothing().when(workflowRespository).persist(any(Workflow.class));
    }

    @AfterEach
    public void tearDown() {
        Log.infof("Tearing down test");
        Mockito.reset(spiritAnimalService, workflowRespository);
    }

    @Test
    public void testFeedback() {
        given()
                .with().body(FEEDBACK_JSON)
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().post("/spiritanimals/feedback")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is("Peppermint Patty"))
                .body("feedback", is(FEEDBACK_JSON.feedback()));

    }
}
