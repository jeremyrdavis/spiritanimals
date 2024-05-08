package io.arrogantprogrammer.spiritanimals.workflow;

import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowService;
import io.quarkus.logging.Log;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class WorkflowResource02TestWhatIs {

    @InjectMock
    WorkflowService workflowService;

    @BeforeEach
    public void setUp() {
        Log.infof("Setting up test");
        Mockito.when(workflowService.whatIs(any(Long.class)))
                .thenReturn(new WorkflowRecord.Builder().withId(1L).withName("Peppermint Patty").withSpiritAnimal("Moose").notLiked().withWhatIs(WorkflowTestUtils.WHAT_IS_A_MOOSE).build());
    }

    @Test
    public void testWhatIs() {
        Log.infof("Testing assign spirit animal");
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .with().body("Peppermint Patty")
                .when().post("/spiritanimals/assign/")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is("Peppermint Patty"))
                .body("whatIs", is(WorkflowTestUtils.WHAT_IS_A_MOOSE));
    }

}
