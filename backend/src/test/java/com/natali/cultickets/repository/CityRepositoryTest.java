package com.natali.cultickets.repository;

import com.natali.cultickets.model.City;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityRepositoryTest {
    private final Flyway flyway;
//    private final CityRepository cityRepository;

    @Autowired
    CityRepositoryTest(Flyway flyway) {
        this.flyway = flyway;
//        this.cityRepository = cityRepository;
    }

    @AfterEach
    void restoreDatabase() {
        this.flyway.clean();
        this.flyway.migrate();
    }

    @Test
    void getById_id1_Venice() {
        int expectedId = 1;
        String expectedName = "Venice";
        String actualName;
        int actualId;

//        City city = this.cityRepository.getById(expectedId).orElseThrow();
//        actualName = city.getName();
//        actualId = city.getId();
//
//        assertEquals(actualId, expectedId);
//        assertEquals(actualName, expectedName);
    }
}