package io.arrogantprogrammer.spiritanimals.dashboard.infrastructure;

import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalRecord;
import io.arrogantprogrammer.spiritanimals.api.SpiritAnimalService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class DashboardResourceTest {

    static final Logger LOGGER = LoggerFactory.getLogger(DashboardResourceTest.class);

    @InjectMock
    SpiritAnimalService spiritAnimalService;

    @BeforeEach
    public void setUp() {
        LOGGER.info("Setting up test");

        Mockito.when(spiritAnimalService.getSpiritAnimalById(1L))
                .thenReturn(new SpiritAnimalRecord(1L, "Lucy", "Reef Shark", false));

        Mockito.when(spiritAnimalService.allSpiritAnimals())
                .thenReturn(Arrays.asList(
                        new SpiritAnimalRecord(1L, "Lucy", "Reef Shark", false),
                        new SpiritAnimalRecord(2L, "Snoopy", "Polar Bear", false),
                        new SpiritAnimalRecord(3L, "Charlie Brown", "Ferret", false)));              ;
    }

    @Test
    public void testGetById() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/1")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", is("Lucy"));
    }

    @Test
    public void testGetByAll() {
        given()
                .with().contentType(MediaType.APPLICATION_JSON)
                .when().get("/dashboard/")
                .then()
                .statusCode(200).body("$", hasSize(3));
    }
}
