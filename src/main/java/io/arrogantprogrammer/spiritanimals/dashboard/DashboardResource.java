package io.arrogantprogrammer.spiritanimals.dashboard;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowRecord;
import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/dashboard")
public class DashboardResource {

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Inject
    FeedbackService feedbackService;

    @Inject
    WorkflowService workflowService;

    @GET
    @Path("/spiritanimals/{id}")
    public Response getSpiritAnimal(@PathParam("id") final Long id) {
        Log.infof("Getting spirit animal by id: %s", id);
        return Response.ok(spiritAnimalService.getSpiritAnimalById(id)).build();
    }
    @GET
    @Path("/spiritanimals")
    public Response allSpiritAnimals() {
         Log.infof("Getting all spirit animals");
        return Response.ok(spiritAnimalService.allSpiritAnimals()).build();
    }
    @GET
    @Path("/feedback")
    public Response allFeedback() {
         Log.infof("Getting all feedback");
         List<FeedbackRecord> allFeedback = feedbackService.allFeedback();
         Log.debugf("Returning %s feedbacks", allFeedback.size());
        return Response.ok().entity(allFeedback).build();
    }
    @GET
    @Path("/feedback/positive")
    public Response allPositiveFeedback() {
        Log.infof("Getting all feedback");
        List<FeedbackRecord> allFeedback = feedbackService.allPositiveFeedback();
        Log.debugf("Returning %s feedbacks", allFeedback.size());
        return Response.ok().entity(allFeedback).build();
    }
    @GET
    @Path("/feedback/negative")
    public Response allNegativeFeedback() {
        Log.infof("Getting all feedback");
        List<FeedbackRecord> allFeedback = feedbackService.allNegativeFeedback();
        Log.debugf("Returning %s feedbacks", allFeedback.size());
        return Response.ok().entity(allFeedback).build();
    }
    @GET
    @Path("/feedback/neutral")
    public Response allNeutralFeedback() {
        Log.infof("Getting all feedback");
        List<FeedbackRecord> allFeedback = feedbackService.allNeutralFeedback();
        Log.debugf("Returning %s feedbacks", allFeedback.size());
        return Response.ok().entity(allFeedback).build();
    }
    @GET
    @Path("/workflow/all")
    public Response allWorkflows() {
        List<WorkflowRecord> allWorkflows = workflowService.allWorkflows();
        return Response.ok().entity(allWorkflows).build();
    }
}
