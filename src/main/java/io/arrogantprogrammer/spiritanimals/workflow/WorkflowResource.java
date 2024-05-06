package io.arrogantprogrammer.spiritanimals.workflow;


import io.arrogantprogrammer.spiritanimals.workflow.api.WorkflowService;
import io.arrogantprogrammer.spiritanimals.workflow.api.SpiritAnimalWorkflow;
import io.arrogantprogrammer.spiritanimals.domain.FeedbackJson;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/spiritanimals")
@Produces(MediaType.APPLICATION_JSON)
public class WorkflowResource {

    static final Logger LOGGER = LoggerFactory.getLogger(WorkflowResource.class);

    @Inject
    WorkflowService workflowService;

    @POST
    @Path("/assign")
    @Transactional
    public Response assignSpiritAnimal(final String name) {
        LOGGER.debug("Assigning spirit animal for {}", name);
        SpiritAnimalWorkflow spiritAnimalWorkflow = workflowService.assignSpiritAnimalFor(name);
        LOGGER.debug("Assigned spirit animal for {}: {}", name, spiritAnimalWorkflow.spiritAnimal());
        return Response.status(201).entity(spiritAnimalWorkflow).build();
    }

    @POST
    @Path("/whatIs")
    @Transactional
    public Response whatIs(final Long id) {
        LOGGER.debug("whatIs for id: {}", id);
        SpiritAnimalWorkflow whatIsResult = workflowService.whatIs(id);
        return Response.status(201).entity(whatIsResult).build();
    }

    @POST
    @Path("/poem")
    @Transactional
    public Response poem(final Long id) {
        LOGGER.debug("Poem for spiritAnimalWorkflow:{}", id);
        SpiritAnimalWorkflow spiritAnimalWorkflow = workflowService.writeAPoem(id);
        return Response.status(201).entity(spiritAnimalWorkflow).build();
    }

    @POST
    @Path("/addToPoem")
    @Transactional
    public Response addToPoem(final Long id) {
        LOGGER.debug("Adding to poem for spiritAnimalWorkflow:{}", id);
        SpiritAnimalWorkflow spiritAnimalWorkflow = workflowService.addToPoem(id);
        return Response.status(201).entity(spiritAnimalWorkflow).build();
    }

    @POST
    @Path("/like")
    @Transactional
    public Response like(final Long id) {
        LOGGER.debug("Liking spirit animal for id: {}", id);
        SpiritAnimalWorkflow spiritAnimalWorkflow = workflowService.like(id);
        return Response.status(200).entity(spiritAnimalWorkflow).build();
    }

    @POST
    @Path("/feedback")
    @Transactional
    public Response feedback(FeedbackJson feedbackJson) {
        LOGGER.debug("Feedback spirit animal for id: {}", feedbackJson.id());
        SpiritAnimalWorkflow spiritAnimalWorkflow = workflowService.feedback(feedbackJson.id(), feedbackJson.feedback());
        return Response.status(200).entity(spiritAnimalWorkflow).build();
    }

}
