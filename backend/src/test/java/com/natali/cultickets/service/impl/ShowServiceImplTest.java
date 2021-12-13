//package com.natali.cultickets.service.impl;
//
//import com.natali.cultickets.dto.ShowDto;
//import com.natali.cultickets.mapstruct.ShowMapper;
//import com.natali.cultickets.model.Show;
//import com.natali.cultickets.repository.ShowRepository;
//import com.natali.cultickets.service.ShowService;
//import com.natali.cultickets.service.exception.ServiceException;
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
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class ShowServiceImplTest {
//    private final Flyway flyway;
//    private final ShowService showService;
//    private final ShowRepository showRepository;
//    private final ShowMapper showMapper;
//
//    @Autowired
//    ShowServiceImplTest(Flyway flyway, ShowService showService, ShowRepository showRepository, ShowMapper showMapper) {
//        this.flyway = flyway;
//        this.showService = showService;
//        this.showRepository = showRepository;
//        this.showMapper = showMapper;
//    }
//
//    @AfterEach
//    void restoreDatabase() {
//        this.flyway.clean();
//        this.flyway.migrate();
//    }
//
//    @Test
//    void findShows_TheaterId0TypeId0_all2show() {
//        int expectedShowsAmount = 2;
//        int testTypeId = 0, testTheaterId = 0;
//        int actualShowAmount;
//
//        List<ShowDto> resultShows = this.showService.findShows(testTheaterId, testTypeId);
//        actualShowAmount = resultShows.size();
//
//        assertEquals(actualShowAmount, expectedShowsAmount);
//    }
//
//    @Test
//    void findShows_TheaterId1TypeId0_1show() {
//        int expectedTheaterId = 1;
//        int expectedShowsAmount = 1;
//        int testTypeId = 0;
//        int actualShowAmount;
//
//        List<ShowDto> resultShows = this.showService.findShows(expectedTheaterId, testTypeId);
//        actualShowAmount = resultShows.size();
//
//        assertEquals(actualShowAmount, expectedShowsAmount);
//        assertTrue(checkAllTheatersType(resultShows, expectedTheaterId));
//    }
//
//    @Test
//    void findShows_TypeId1TheaterId0_2show() {
//        int expectedShowId = 1;
//        int expectedShowsAmount = 2;
//        int testTheaterId = 0;
//        int actualShowAmount;
//
//        List<ShowDto> resultShows = this.showService.findShows(testTheaterId, expectedShowId);
//        actualShowAmount = resultShows.size();
//
//        assertEquals(actualShowAmount, expectedShowsAmount);
//        assertTrue(checkAllShowsType(resultShows, expectedShowId));
//    }
//
//    @Test
//    void findShows_TypeId3TheaterId1_0shows() {
//        int testShowId = 3;
//        int expectedShowsAmount = 0;
//        int testTheaterId = 1;
//        int actualShowAmount;
//
//        List<ShowDto> resultShows = this.showService.findShows(testTheaterId, testShowId);
//        actualShowAmount = resultShows.size();
//
//        assertEquals(actualShowAmount, expectedShowsAmount);
//    }
//
//
//    @Test
//    void findShows_TypeId1NonExistentTheaterId3_throwsServiceException() {
//        int testShowId = 1;
//        int testTheaterId = 3;
//
//        assertThrows(ServiceException.class, () -> this.showService.findShows(testTheaterId, testShowId));
//    }
//
//    @Test
//    void findShows_NonExistentTypeId10TheaterId1_throwsServiceException() {
//        int testShowId = 10;
//        int testTheaterId = 1;
//
//        assertThrows(ServiceException.class, () -> this.showService.findShows(testTheaterId, testShowId));
//    }
//
//    @Test
//    void updateShow_setTestNameAndDescription_NameAndDescriptionChanged() {
//        int testShowId = 1;
//        String testName = "TestShow";
//        String testDescription = "TestDescription";
//
//        Show show = this.showRepository.findById(testShowId).orElseThrow();
//        ShowDto showDto = this.showMapper.showToShowDto(show);
//        showDto.setName(testName);
//        showDto.setDescription(testDescription);
//
//        this.showService.updateShow(showDto);
//        Show showAfterUpdate = this.showRepository.findById(testShowId).orElseThrow();
//
//        assertEquals(testName, showAfterUpdate.getName());
//        assertEquals(testDescription, showAfterUpdate.getDescription());
//    }
//
//    @Test
//    void saveShow_createShow_showSavedWithCorrectNameAnsDescription() {
//        String testName = "TestShowToSave";
//        String testDescription = "Test Description To Save";
//        int someShowId = 1;
//
//        Show showToChange = this.showRepository.findById(someShowId).orElseThrow();
//        ShowDto showDto = this.showMapper.showToShowDto(showToChange);
//
//        showDto.setName(testName);
//        showDto.setDescription(testDescription);
//
//        Show actualShow = this.showService.saveShow(showDto);
//
//        assertNotEquals(someShowId, actualShow.getId());
//        assertEquals(testName, actualShow.getName());
//        assertEquals(testDescription, actualShow.getDescription());
//    }
//
//    @Test
//    void deleteShow_deleteShowWithId2_showDeleted() {
//        int idShowToDelete = 2;
//        Show showToDelete = this.showRepository.findById(idShowToDelete).orElseThrow();
//        ShowDto showDto = this.showMapper.showToShowDto(showToDelete);
//
//        this.showService.deleteShow(showDto);
//
//        Optional<Show> resultShow = this.showRepository.findById(idShowToDelete);
//
//        assertTrue(resultShow.isEmpty());
//    }
//
//
//    boolean checkAllTheatersType(List<ShowDto> shows, int expectedType) {
//        return shows.stream()
//                .map(e -> e.getTheater().getId() == expectedType)
//                .reduce((a, b) -> a && b)
//                .orElseThrow();
//    }
//
//    boolean checkAllShowsType(List<ShowDto> shows, int expectedType) {
//        return shows.stream()
//                .map(e -> e.getShowType().getId() == expectedType)
//                .reduce((a, b) -> a && b)
//                .orElseThrow();
//    }
//
//}