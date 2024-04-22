package io.arrogantprogrammer.spiritanimals.infrastructure.rest;


import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalWorkflow;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalServiceImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/spiritanimals")
@Produces(MediaType.APPLICATION_JSON)
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
    public Response whatIs(final Long id) {
        LOGGER.debug("whatIs for id: {}", id);
        SpiritAnimalWorkflow whatIsResult = spiritAnimalService.whatIs(id);
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
