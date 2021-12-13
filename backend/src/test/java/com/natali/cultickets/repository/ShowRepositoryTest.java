//package com.natali.cultickets.repository;
//
//import com.natali.cultickets.model.Show;
//import com.natali.cultickets.model.ShowType;
//import com.natali.cultickets.model.Theater;
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
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class ShowRepositoryTest {
//    private final Flyway flyway;
//    private final ShowRepository showRepository;
//    private final TheaterRepository theaterRepository;
//    private final ShowTypeRepository showTypeRepository;
//
//    @Autowired
//    ShowRepositoryTest(Flyway flyway, ShowRepository showRepository, TheaterRepository theaterRepository, ShowTypeRepository showTypeRepository) {
//        this.flyway = flyway;
//        this.showRepository = showRepository;
//        this.theaterRepository = theaterRepository;
//        this.showTypeRepository = showTypeRepository;
//    }
//
//    @AfterEach
//    void restoreDatabase() {
//        this.flyway.clean();
//        this.flyway.migrate();
//    }
//
//    @Test
//    void findAll_all_2() {
//        int expectedSize = 2, actualSize;
//
//        List<Show> shows = this.showRepository.findAll();
//        actualSize = shows.size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//
//    @Test
//    void findByTheater_theaterId1_1() {
//        int expectedSize = 1, actualSize;
//        int testTheaterId = 2;
//        int testShowIndex = 0;
//
//        Theater testTheater = this.theaterRepository.findById(testTheaterId).orElseThrow();
//
//        List<Show> testShows = this.showRepository.findByTheater(testTheater);
//        actualSize = testShows.size();
//        assertEquals(expectedSize, actualSize);
//
//        Show testShow = testShows.get(testShowIndex);
//
//        assertEquals(testTheater, testShow.getTheater());
//    }
//
//    @Test
//    void findByTheater_nullTheater_0() {
//        Theater testNullTheater = null;
//        int expectedSize = 0, actualSize;
//
//        List<Show> testShows = this.showRepository.findByTheater(testNullTheater);
//        actualSize = testShows.size();
//
//        assertEquals(expectedSize, actualSize);
//    }
//    @Test
//    void findByShowType_type1_2() {
//        int expectedSize = 2, actualSize;
//        int testShowTypeId = 1;
//        int testShowIndex = 0;
//
//        ShowType showType = this.showTypeRepository.findById(testShowTypeId).orElseThrow();
//
//        List<Show> testShows = this.showRepository.findByShowType(showType);
//        actualSize = testShows.size();
//        assertEquals(expectedSize, actualSize);
//
//        Show testShow = testShows.get(testShowIndex);
//
//        assertEquals(showType, testShow.getShowType());
//    }
//
//    @Test
//    void findByTheaterAndShowType_theaterId1showTypeId1_1() {
//        int expectedSize = 1, actualSize;
//        int testTheaterId = 2;
//        int testShowIndex = 0;
//
//        Theater testTheater = this.theaterRepository.findById(testTheaterId).orElseThrow();
//
//        List<Show> testShows = this.showRepository.findByTheater(testTheater);
//        actualSize = testShows.size();
//        assertEquals(expectedSize, actualSize);
//
//        Show testShow = testShows.get(testShowIndex);
//
//        assertEquals(testTheater, testShow.getTheater());
//    }
//
//    @Test
//    void updateShow_id1changeNameAndDescription_changed() {
//        String expectedName = "test", expectedDescription = "Some cool test phrases";
//        String actualName, actualDescription;
//        int testId = 1;
//
//        this.showRepository.updateShow(testId, expectedName, expectedDescription);
//
//        Show show = this.showRepository.findById(testId).orElseThrow();
//        actualName = show.getName();
//        actualDescription = show.getDescription();
//
//        assertEquals(expectedName, actualName);
//        assertEquals(expectedDescription, actualDescription);
//    }
//}