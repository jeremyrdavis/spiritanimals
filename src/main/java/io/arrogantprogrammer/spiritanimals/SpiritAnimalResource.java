package io.arrogantprogrammer.spiritanimals;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/spiritanimals")
public class SpiritAnimalResource {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalResource.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @GET
    public Animal getAnimal() {
        LOGGER.info("Getting animal");
        return spiritAnimalService.getAnimal();
    }
}
