package com.natali.cultickets.controller;

//import com.natali.cultickets.service.impl.ShowServiceImpl;

import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.dto.TheatreDto;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.service.GenreService;
import com.natali.cultickets.service.ShowService;
import com.natali.cultickets.service.TheatreService;
import com.natali.cultickets.service.UserService;
import com.natali.cultickets.service.impl.ShowServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping(value = "/shows")
@RestController
public class ShowsController {

    private final ShowService showService;
    private final GenreService genreService;
    private final TheatreService theatreService;
    private final UserService userService;

    @Autowired
    public ShowsController(
            ShowServiceImpl showService,
            GenreService genreService,
            TheatreService theaterService,
            UserService userService
    ) {
        this.showService = showService;
        this.genreService = genreService;
        this.theatreService = theaterService;
        this.userService = userService;
    }

//    @GetMapping("/filters")
//    ResponseEntity<Map<String, Object>> getShowFilters() {
//        ResponseEntity<Map<String, Object>> responseEntity;
//        Map<String, Object> responseData = new HashMap<>();
//        try {
////            List<ShowTypeDto> showTypes = this.showTypeService.findAll();
////            List<TheaterDto> theaters = this.theaterService.findAll();
////            responseData.put("theaters", theaters);
////            responseData.put("showTypes", showTypes);
//            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
//        } catch (Exception e) {
//            responseData.put("message", "Failed to get filters.");
//            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
//            log.error("Failed to get filters.", e);
//        }
//        return responseEntity;
//    }

    @GetMapping("/theater")
//    ResponseEntity<Map<String, List<ShowDto>>> getShows(@PathVariable int theaterId, @PathVariable int showTypeId) {
    ResponseEntity<Map<String, List<ShowDto>>> getShows() {
        ResponseEntity<Map<String, List<ShowDto>>> responseEntity;
        Map<String, List<ShowDto>> responseData = new HashMap<>();
        try {
//            List<ShowDto> shows = this.showService.findShows(theaterId, showTypeId);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get shows", e);
        }
        return responseEntity;
    }

    @GetMapping("/userId={userId}&theatreId={theatreId}&genreId={genreId}")
    ResponseEntity<Map<String, Object>> getShows(@PathVariable int userId,
                                                 @PathVariable int theatreId,
                                                 @PathVariable int genreId,
                                                 Principal principal) {
        String login = principal.getName();
        AuthInfo authInfo = userService.findByLogin(login);
        int user_id = authInfo.getUserId();

        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            List<ShowDto> shows = this.showService.findShows(user_id, theatreId, genreId, userId);
            responseData.put("shows", shows);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put("message", "Failed to get suitable shows.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get suitable shows", e);
        }
        return responseEntity;
    }

    @GetMapping("/showId={showId}")
    ResponseEntity<Map<String, Object>> getScheduledShows(@PathVariable int showId, Principal principal) {
        String login = principal.getName();
        AuthInfo authInfo = userService.findByLogin(login);
        int userId = authInfo.getUserId();

        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            ShowDto show = this.showService.getShowInfo(userId, showId);
            List<ShowDto> shows = this.showService.findScheduledShowsByShow(userId, showId);
            responseData.put("show", show);
            responseData.put("scheduled_shows", shows);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put("message", "Failed to get suitable shows.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get suitable shows", e);
        }
        return responseEntity;
    }

    @GetMapping("/filters")
    ResponseEntity<Map<String, Object>> getTheatres(Principal principal) {
        String login = principal.getName();
        AuthInfo authInfo = userService.findByLogin(login);
        int user_id = authInfo.getUserId();

        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            List<TheatreDto> theatres = this.theatreService.findAll(user_id);
            List<GenreDto> genres = this.genreService.findAll(user_id);
            responseData.put("theatres", theatres);
            responseData.put("genres", genres);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put("message", "Failed to get suitable shows.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get suitable shows", e);
        }
        return responseEntity;
    }

    @GetMapping("/theatre={id}")
    ResponseEntity<Map<String, Object>> getTheatres(@PathVariable int id, Principal principal) {
        String login = principal.getName();
        AuthInfo authInfo = userService.findByLogin(login);
        int userId = authInfo.getUserId();

        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            List<ShowDto> shows = this.showService.getByTheatre(userId, id);
            responseData.put("shows", shows);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put("message", "Failed to get suitable shows.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get suitable shows", e);
        }
        return responseEntity;
    }

}
