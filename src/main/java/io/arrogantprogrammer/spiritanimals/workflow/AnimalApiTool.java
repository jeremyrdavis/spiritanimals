package io.arrogantprogrammer.spiritanimals.workflow;

import dev.langchain4j.agent.tool.Tool;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class AnimalApiTool {

    @RestClient
    AnimalApiRestClient animalApiRestClient;

    @Tool("get information about an animal")
    public AnimalRecord getAnimalDetails(String animal) {
        Log.debugf("Getting information about the spirit animal: %s", animal);
        List<AnimalRecord> animalRecords = animalApiRestClient.getAnimalDetails(animal);
        Log.debugf("Got information about the spirit animal: %s", animalRecords.size());
        return animalRecords.get(0);
    };
}
