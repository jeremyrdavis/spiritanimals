package io.arrogantprogrammer.spiritanimals;

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
    public Animal getAnimal(@RestQuery String name);

    @GET
    public List<Animal> getAnimals(@RestQuery String name);

}
