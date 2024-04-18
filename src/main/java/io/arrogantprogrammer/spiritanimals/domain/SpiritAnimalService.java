package io.arrogantprogrammer.spiritanimals.domain;

import io.arrogantprogrammer.spiritanimals.AnimalRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class SpiritAnimalService {

    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalService.class);

    static final String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @RestClient
    AnimalRestClient animalRestClient;

    public AnimalJson getAnimal() {

        List<AnimalJson> animalJsonList = animalRestClient.getAnimals("cat");
        LOGGER.debug("retrieved {} animals", animalJsonList.size());

        if(animalJsonList.size() > 0) {
            return animalJsonList.get(0);
        } else {
            return null;
        }
    }
}
