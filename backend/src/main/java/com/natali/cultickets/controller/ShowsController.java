package com.natali.cultickets.controller;

import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.dto.ShowTypeDto;
import com.natali.cultickets.dto.TheaterDto;
import com.natali.cultickets.service.ShowService;
import com.natali.cultickets.service.ShowTypeService;
import com.natali.cultickets.service.TheaterService;
//import com.natali.cultickets.service.impl.ShowServiceImpl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/shows")
@RestController
public class ShowsController {

//    private final ShowService showService;
//    private final ShowTypeService showTypeService;
//    private final TheaterService theaterService;

    @Autowired
    public ShowsController(
//            ShowServiceImpl showService,
//                           ShowTypeService showTypeService, TheaterService theaterService
)                           {
//        this.showService = showService;
//        this.showTypeService = showTypeService;
//        this.theaterService = theaterService;
    }

    @GetMapping("/filters")
    ResponseEntity<Map<String, Object>> getShowFilters() {
        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
//            List<ShowTypeDto> showTypes = this.showTypeService.findAll();
//            List<TheaterDto> theaters = this.theaterService.findAll();
//            responseData.put("theaters", theaters);
//            responseData.put("showTypes", showTypes);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put("message", "Failed to get filters.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get filters.", e);
        }
        return responseEntity;
    }

    @GetMapping("/theater")
//    ResponseEntity<Map<String, List<ShowDto>>> getShows(@PathVariable int theaterId, @PathVariable int showTypeId) {
    ResponseEntity<Map<String, List<ShowDto>>> getShows() {
        ResponseEntity<Map<String, List<ShowDto>>> responseEntity;
        Map<String, List<ShowDto>> responseData = new HashMap<>();
        try {
//            List<ShowDto> shows = this.showService.findShows(theaterId, showTypeId);
            List<ShowDto> shows = List.of(new ShowDto(1, "Theatre 1", "Good one", null),
                    new ShowDto(2, "Theatre 2", "Good too", null));
            responseData.put("shows", shows);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get shows", e);
        }
        return responseEntity;
    }

}
