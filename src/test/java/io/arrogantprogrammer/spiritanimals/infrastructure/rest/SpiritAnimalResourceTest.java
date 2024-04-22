package io.arrogantprogrammer.spiritanimals.infrastructure.rest;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalService;
import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalWorkflow;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalAssignment;
import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalTestUtils;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class SpiritAnimalResourceTest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalResourceTest.class);

    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        SpiritAnimalTestUtils.addAnimals();
        Mockito.when(spiritAnimalService.assignSpiritAnimalFor("Jar Jar Binks"))
                .thenReturn(new SpiritAnimalWorkflow(1L, "Jar Jar Binks", "Reef Shark"));

        Mockito.when(spiritAnimalService.whatIs(new SpiritAnimalWorkflow(1L, "Jar Jar Binks", "Reef Shark")))
                .thenReturn(new SpiritAnimalWorkflow.Builder().withId(1L).withName("Jar Jar Binks").withSpiritAnimal("Reef Shark").withWhatIs(whatIsText).build());
    }

    @Test
    public void testAssignSpiritAnimal() {
        LOGGER.info("Testing assignSpiritCharacter");

        given()
                .with().body("Jar Jar Binks")
                .with().contentType("application/json")
                .when().post("/spiritanimals/assign")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", is("Jar Jar Binks"))
                .body("spiritAnimal", notNullValue());
    }

    @Test
    public void testWhatIs(){
        LOGGER.info("Testing whatIs");

        given()
                .with().body(whatIsRequestJson)
                .with().contentType("application/json")
                .when().post("/spiritanimals/whatIs")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", is("Jar Jar Binks"))
                .body("spiritAnimal", notNullValue())
                .body("whatIs", is(whatIsText));
    }

    @Test
    public void testPoem(){
        LOGGER.info("Testing whatIs");

        given()
                .with().body("1")
                .with().contentType("application/json")
                .when().post("/spiritanimals/poem")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", is("Jar Jar Binks"))
                .body("spiritAnimal", notNullValue())
                .body("whatIs", is(whatIsText))
                .body("poem", is(poemText));
    }


    String whatIsRequestJson = """
            {
                "id": 1,
                "name": "Jar Jar Binks",
                "spiritAnimal": "Reef Shark",
                "accepted": false,
                "whatIs": null,
                "poem": null,
                "updatedPoem": null,
                "feedback": null
            }
            """;

    String whatIsText = """
            A reef shark is a type of shark that typically inhabits coral reef ecosystems in tropical and subtropical waters around the world. They belong to the family Carcharhinidae and are known for their streamlined bodies, sharp teeth, and often striking appearance. Reef sharks play a crucial role in maintaining the health and balance of coral reef ecosystems by regulating prey populations and contributing to overall biodiversity.
            
            There are several species of reef sharks, including the blacktip reef shark, whitetip reef shark, grey reef shark, and nurse shark, among others. These sharks vary in size, behavior, and habitat preferences, but they are all adapted to thrive in reef environments.
            
            Reef sharks are carnivorous predators, feeding on a variety of marine life such as fish, crustaceans, and cephalopods. They are typically not considered dangerous to humans unless provoked, and most interactions between reef sharks and humans are peaceful.
            
            Despite their importance to reef ecosystems, reef sharks face threats from overfishing, habitat destruction, and pollution, which have led to population declines in some areas. Conservation efforts aimed at protecting reef habitats and regulating fishing practices are essential for ensuring the survival of reef shark species and the health of coral reef ecosystems.            
            """;
    String poemText = """
            In the azure depths where corals sway,
            Amidst the whispers of the brine's ballet,
            There glides a creature, sleek and bold,
            In silver armor, a tale untold.
            
            Ode to thee, reef shark divine,
            With fins like sails, in currents entwine,
            Your silhouette, a shadow's dance,
            In Neptune's realm, you enchant and entrance.
            
            Beneath the moon's soft, shimmering light,
            You roam the ocean's velvet night,
            A guardian of secrets, silent and true,
            In your presence, mysteries accrue.
            
            Oh, how your gaze, with depth profound,
            Reflects the wisdom of the ocean's sound,
            Eyes that hold the stories of the deep,
            In their depths, ancient memories sleep.
            
            You are the poet of the deep sea's lore,
            With each graceful move, you implore,
            To cherish the treasures of the marine,
            And honor the mysteries unseen.
            
            Reef shark, your essence, a symphony rare,
            In every ripple, in every flare,
            You are the spirit of the ocean's heart,
            A testament to its eternal art.
            """;
}
