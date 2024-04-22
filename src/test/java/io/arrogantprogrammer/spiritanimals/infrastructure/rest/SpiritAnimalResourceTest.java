package io.arrogantprogrammer.spiritanimals.infrastructure.rest;

import io.arrogantprogrammer.spiritanimals.domain.SpiritAnimalTestUtils;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class SpiritAnimalResourceTest {
    static final Logger LOGGER = LoggerFactory.getLogger(SpiritAnimalResourceTest.class);

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");
        SpiritAnimalTestUtils.addAnimals();
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
}
