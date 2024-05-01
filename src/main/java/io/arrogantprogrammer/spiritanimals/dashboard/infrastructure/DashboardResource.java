package io.arrogantprogrammer.spiritanimals.dashboard.infrastructure;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/dashboard")
public class DashboardResource {

    static final Logger LOGGER = LoggerFactory.getLogger(DashboardResource.class);

    @Inject
    SpiritAnimalService spiritAnimalService;

    @GET
    @Path("/{id}")
    public Response getSpiritAnimal(@PathParam("id") final Long id) {
        return Response.ok(spiritAnimalService.getSpiritAnimalById(id)).build();
    }

    @GET
    @Path("/")
    public Response allSpiritAnimals() {
        return Response.ok(spiritAnimalService.allSpiritAnimals()).build();
    }

}
