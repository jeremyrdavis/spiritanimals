package io.arrogantprogrammer.spiritanimals.infrastructure.rest;


import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.domain.SpritAnimalAssignmentRecord;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/spiritanimals")
public class SpiritAnimalResource {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalResource.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @GET
    @Path("/assign")
    @Transactional
    public SpritAnimalAssignmentRecord getAnimal(@QueryParam("name") final String name) {
        LOGGER.info("Getting animal");
        return spiritAnimalService.assignSpiritAnimalFor(name);
    }
}
