package io.arrogantprogrammer.spiritanimals.infrastructure.rest;


import dev.langchain4j.agent.tool.P;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.domain.SpritAnimalAssignmentRecord;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@Path("/spiritanimals")
public class SpiritAnimalResource {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalResource.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @POST
    @Path("/assign")
    @Transactional
    public Response assignSpiritAnimal(final String name) {
        LOGGER.debug("Assigning spirit animal for {}", name);
        SpritAnimalAssignmentRecord spritAnimalAssignmentRecord = spiritAnimalService.assignSpiritAnimalFor(name);
        LOGGER.debug("Assigned spirit animal for {}: {}", name, spritAnimalAssignmentRecord.animalName());
        return Response.status(201).entity(spritAnimalAssignmentRecord).build();
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
