package com.natali.cultickets.repository;

import com.natali.cultickets.model.City;
import com.natali.cultickets.model.ShowState;
import java.util.List;
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
class ShowStateRepositoryTest {
    private final Flyway flyway;
    private final ShowStateRepository showStateRepository;

    @Autowired
    ShowStateRepositoryTest(Flyway flyway, ShowStateRepository showStateRepository) {
        this.flyway = flyway;
        this.showStateRepository = showStateRepository;
    }

    @AfterEach
    void restoreDatabase() {
        this.flyway.clean();
        this.flyway.migrate();
    }

    @Test
    void findById_id1_name() {
        int expectedId = 1;
        String expectedName = "Active";
        String actualName;
        int actualId;

        ShowState showState = this.showStateRepository.findById(expectedId).orElseThrow();
        actualName = showState.getName();
        actualId = showState.getId();

        assertEquals(actualId, expectedId);
        assertEquals(actualName, expectedName);
    }

    @Test
    void findAll_all_3() {
        int expectedAmount = 3, actualAmount;

        List<ShowState> showStates = this.showStateRepository.findAll();
        actualAmount = showStates.size();

        assertEquals(expectedAmount, actualAmount);
    }
}