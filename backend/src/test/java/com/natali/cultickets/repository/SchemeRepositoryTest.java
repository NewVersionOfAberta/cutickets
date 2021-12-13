//package com.natali.cultickets.repository;
//
////import com.natali.cultickets.model.Scheme;
//import java.util.List;
//import java.util.Optional;
//import org.flywaydb.core.Flyway;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class SchemeRepositoryTest {
//    private final Flyway flyway;
//    private final SchemeRepository schemeRepository;
//    @Autowired
//    SchemeRepositoryTest(Flyway flyway, SchemeRepository schemeRepository) {
//        this.flyway = flyway;
//        this.schemeRepository = schemeRepository;
//    }
//
//    @AfterEach
//    void restoreDatabase() {
//        this.flyway.clean();
//        this.flyway.migrate();
//    }
//
//    @Test
//    void findById_id2_3rows4maxSeatsAmount2idTheaterHall() {
//        int     expectedId = 2,
//                expectedMaxSeatsAmount = 4,
//                expectedRows = 3,
//                expectedTheaterHallId = 2,
//                actualId,
//                actualMaxSeatsAmount,
//                actualRows,
//                actualTheaterHallId;
//        Scheme scheme = this.schemeRepository.findById(expectedId).orElseThrow();
//
//        actualId = scheme.getId();
//        actualRows = scheme.getRowsAmount();
//        actualMaxSeatsAmount = scheme.getMaxSeatsAmount();
//        actualTheaterHallId = scheme.getTheaterHall().getId();
//
//        assertEquals(actualId, expectedId);
//        assertEquals(actualRows, expectedRows);
//        assertEquals(actualMaxSeatsAmount, expectedMaxSeatsAmount);
//        assertEquals(actualTheaterHallId, expectedTheaterHallId);
//    }
//
//    @Test
//    void findById_id5_null() {
//        int testId = 5;
//
//        Optional<Scheme> scheme = this.schemeRepository.findById(testId);
//
//        assertTrue(scheme.isEmpty());
//    }
//
//    @Test
//    void findAll_all_size2() {
//        int expectedSize = 2, actualSize;
//
//        List<Scheme> all = this.schemeRepository.findAll();
//        actualSize = all.size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//}