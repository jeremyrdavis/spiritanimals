package io.arrogantprogrammer.spiritanimals;

import io.arrogantprogrammer.spiritanimals.domain.AnimalJson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

@Path("/v1/animals")
@ClientHeaderParam(name = "x-api-key", value = "${api-ninjas-key}")
@RegisterRestClient
public interface AnimalRestClient {

    @GET
    public AnimalJson getAnimal(@RestQuery String name);

    @GET
    public List<AnimalJson> getAnimals(@RestQuery String name);

}
