package io.arrogantprogrammer.spiritanimals.feedback;

import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackRecord;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/feedback")
public class FeedbackResource {

    @Inject
    FeedbackService feedbackService;

    @GET
    public Response allFeedback() {
        List<FeedbackRecord> allFeedback = feedbackService.allFeedback();
        Log.debugf("Returning all feedback: %s", allFeedback);
        return Response.ok().entity(allFeedback).build();
    }
}
