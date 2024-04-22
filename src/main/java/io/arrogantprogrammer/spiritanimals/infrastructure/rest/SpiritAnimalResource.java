package io.arrogantprogrammer.spiritanimals.infrastructure.rest;


import dev.langchain4j.agent.tool.P;
import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalWorkflow;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalServiceImpl;
import io.arrogantprogrammer.spiritanimals.infrastructure.rest.domain.SpritAnimalAssignmentRecord;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/spiritanimals")
public class SpiritAnimalResource {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalResource.class);

    @Inject
    SpiritAnimalServiceImpl spiritAnimalService;

    @POST
    @Path("/assign")
    @Transactional
    public Response assignSpiritAnimal(final String name) {
        LOGGER.debug("Assigning spirit animal for {}", name);
        SpiritAnimalWorkflow spiritAnimalWorkflow = spiritAnimalService.assignSpiritAnimalFor(name);
        LOGGER.debug("Assigned spirit animal for {}: {}", name, spiritAnimalWorkflow.spiritAnimal());
        return Response.status(201).entity(spiritAnimalWorkflow).build();
    }

    @POST
    @Path("/whatIs")
    @Transactional
    public Response whatIs(SpiritAnimalWorkflow spiritAnimalWorkflow) {
        LOGGER.debug("What is {}", spiritAnimalWorkflow.spiritAnimal());
        SpiritAnimalWorkflow whatIsResult = spiritAnimalService.whatIs(spiritAnimalWorkflow);
        return Response.status(201).entity(whatIsResult).build();
    }

    @POST
    @Path("/poem")
    @Transactional
    public Response poem(final Long id) {
        LOGGER.debug("Poem for spiritAnimalWorkflow:{}", id);
        SpiritAnimalWorkflow spiritAnimalWorkflow = spiritAnimalService.writeAPoem(id);
        return Response.status(201).entity(spiritAnimalWorkflow).build();
    }



//    @GET
//    @Path("/")
//    public Response allSpiritAnimals() {
//        return Response.ok(spiritAnimalService.allSpiritAnimals()).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    public Response getSpiritAnimal(@PathParam("id") final Long id) {
//        return Response.ok(spiritAnimalService.getSpiritAnimal(id)).build();
//    }
}
