package io.arrogantprogrammer.spiritanimals.workflow;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v1/animals/")
@ClientHeaderParam(name = "x-api-key", value = "${api-key}")
@RegisterRestClient
public interface AnimalApiRestClient {

    @GET
    public List<AnimalRecord> getAnimalDetails(@QueryParam("name") final String animalName);
}
