package io.arrogantprogrammer.spiritanimals.workflow;


import io.arrogantprogrammer.spiritanimals.domain.FeedbackJson;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/spiritanimals")
@Produces(MediaType.APPLICATION_JSON)
public class WorkflowResource {

    @Inject
    WorkflowService workflowService;

    @POST
    @Path("/assign")
    @Transactional
    public Response assignSpiritAnimal(final String name) {
        Log.debugf("Assigning spirit animal for %s", name);
        WorkflowRecord workflowRecord = workflowService.assignSpiritAnimalFor(name);
        Log.debugf("Assigned spirit animal for %s: %s", name, workflowRecord.spiritAnimal());
        return Response.status(201).entity(workflowRecord).build();
    }

    @POST
    @Path("/whatIs")
    @Transactional
    public Response whatIs(final Long id) {
        Log.debugf("whatIs for id: %s", id);
        WorkflowRecord whatIsResult = workflowService.whatIs(id);
        return Response.status(200).entity(whatIsResult).build();
    }

    @POST
    @Path("/poem")
    @Transactional
    public Response poem(final Long id) {
        Log.debugf("Poem for spiritAnimalWorkflow: %s", id);
        WorkflowRecord workflowRecord = workflowService.writeAPoem(id);
        return Response.status(201).entity(workflowRecord).build();
    }

    @POST
    @Path("/addToPoem")
    @Transactional
    public Response addToPoem(final Long id) {
        Log.debugf("Adding to poem for spiritAnimalWorkflow: %s", id);
        WorkflowRecord workflowRecord = workflowService.addToPoem(id);
        return Response.status(201).entity(workflowRecord).build();
    }

    @POST
    @Path("/like")
    @Transactional
    public Response like(final Long id) {
        Log.debugf("Liking spirit animal for id: %s", id);
        WorkflowRecord workflowRecord = workflowService.like(id);
        return Response.status(200).entity(workflowRecord).build();
    }

    @POST
    @Path("/feedback")
    @Transactional
    public Response feedback(FeedbackJson feedbackJson) {
        Log.debugf("Feedback spirit animal for id: %s", feedbackJson.id());
        WorkflowRecord workflowRecord = workflowService.feedback(feedbackJson.id(), feedbackJson.feedback());
        return Response.status(200).entity(workflowRecord).build();
    }

}
