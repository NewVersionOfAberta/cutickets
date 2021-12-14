package com.natali.cultickets.repository;

import com.natali.cultickets.model.Theatre;
import java.util.List;
import java.util.Optional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class TheaterRepositoryTest {
    private final Flyway flyway;
    private final TheatreRepository theatreRepository;

    @Autowired
    TheaterRepositoryTest(Flyway flyway, TheatreRepository theatreRepository) {
        this.flyway = flyway;
        this.theatreRepository = theatreRepository;
    }

    @AfterEach
    void restoreDatabase() {
        this.flyway.clean();
        this.flyway.migrate();
    }
//
//    @Test
//    void findById_id1_nameAndDescriptionMatch() {
//        int testId = 1;
//        String expectedDescription = "Appropriately named La Fenice (the Phoenix), this Venetian" +
//                " theatre has burned down and risen again from the flames three times since it first opened in 1792.";
//        String expectedName = "Teatro La Fenice";
//        String actualName, actualDescription;
//
//        Optional<Theatre> theater = this.theatreRepository.findById(testId);
//
//        assertTrue(theater.isPresent());
//
//        actualName = theater.get().getName();
//        actualDescription = theater.get().getDescription();
//
//        assertEquals(expectedName, actualName);
//        assertEquals(expectedDescription, actualDescription);
//    }
//
//    @Test
//    void findById_id10_null() {
//        int testId = 10;
//        Optional<Theatre> theater = this.theatreRepository.findById(testId);
//
//        assertTrue(theater.isEmpty());
//    }
//
//    @Test
//    void findAll_all_2() {
//        int expectedAmount = 2;
//        int actualAmount;
//
//        List<Theatre> theatres = this.theatreRepository.findAll();
//        actualAmount = theatres.size();
//
//        assertEquals(expectedAmount, actualAmount);
//    }
}