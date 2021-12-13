package com.natali.cultickets.repository;

import com.natali.cultickets.model.ShowType;
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
class ShowTypeRepositoryTest {
    private final Flyway flyway;
    private final ShowTypeRepository showTypeRepository;

    @Autowired
    ShowTypeRepositoryTest(Flyway flyway, ShowTypeRepository showTypeRepository) {
        this.flyway = flyway;
        this.showTypeRepository = showTypeRepository;
    }

    @AfterEach
    void restoreDatabase() {
        this.flyway.clean();
        this.flyway.migrate();
    }

    @Test
    void findById_id1_name() {
        int expectedId = 1;
        String expectedName = "Play";
        String actualName;
        int actualId;

        ShowType showType = this.showTypeRepository.findById(expectedId).orElseThrow();
        actualName = showType.getName();
        actualId = showType.getId();

        assertEquals(actualId, expectedId);
        assertEquals(actualName, expectedName);
    }

    @Test
    void findAll_all_5() {
        int expectedAmount = 5, actualAmount;

        List<ShowType> showTypes = this.showTypeRepository.findAll();
        actualAmount = showTypes.size();

        assertEquals(expectedAmount, actualAmount);
    }
}