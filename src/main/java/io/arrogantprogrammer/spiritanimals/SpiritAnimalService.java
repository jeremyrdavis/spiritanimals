package io.arrogantprogrammer.spiritanimals;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class SpiritAnimalService {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalService.class);

    @RestClient
    AnimalRestClient animalRestClient;

    public Animal getAnimal() {

        List<Animal> animalList = animalRestClient.getAnimals("cat");
        LOGGER.debug("retrieved {} animals", animalList.size());

        if(animalList.size() > 0) {
            return animalList.get(0);
        } else {
            return null;
        }
    }
}
