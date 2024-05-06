package io.arrogantprogrammer.spiritanimals.dashboard;

import io.arrogantprogrammer.spiritanimals.core.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.feedback.api.FeedbackService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/dashboard")
public class DashboardResource {

    @Inject
    SpiritAnimalService spiritAnimalService;

    @Inject
    FeedbackService feedbackService;

    @GET
    @Path("/{id}")
    public Response getSpiritAnimal(@PathParam("id") final Long id) {
        Log.infof("Getting spirit animal by id: %s", id);
        return Response.ok(spiritAnimalService.getSpiritAnimalById(id)).build();
    }

    @GET
    @Path("/")
    public Response allSpiritAnimals() {
         Log.infof("Getting all spirit animals");
        return Response.ok(spiritAnimalService.allSpiritAnimals()).build();
    }


    @GET
    @Path("/feedback")
    public Response allFeedback() {
         Log.infof("Getting all feedback");
        return Response.ok().entity(feedbackService.allFeedback()).build();
    }
}
