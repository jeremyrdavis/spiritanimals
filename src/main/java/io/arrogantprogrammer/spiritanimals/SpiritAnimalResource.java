package io.arrogantprogrammer.spiritanimals;


import io.arrogantprogrammer.spiritanimals.domain.AnimalJson;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalService;
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
    public AnimalJson getAnimal() {
        LOGGER.info("Getting animal");
        return spiritAnimalService.getAnimal();
    }
}
