package com.natali.cultickets.controller;

import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.service.ShowService;
//import com.natali.cultickets.service.impl.ShowServiceImpl;
import com.natali.cultickets.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    ResponseEntity<Map<String, List<UserDto>>> getUsers() {
//        try {
            Map<String, List<UserDto>> result = new HashMap<>();
            result.put("users", userService.getAllUsers());
            return new ResponseEntity<>(result, HttpStatus.CREATED);
//        } catch (Exception e) {
//            responseEntity = new ResponseEntity<>("Failed to create show.", HttpStatus.INTERNAL_SERVER_ERROR);
//            log.error("Failed to create show", e);
//        }
//    return responseEntity;
}

    @PostMapping("/show/submit")
    ResponseEntity<String> submitShow(@RequestBody ShowDto showDto) {
        ResponseEntity<String> responseEntity;
        try {
//            this.showService.saveShow(showDto);
            responseEntity = new ResponseEntity<>("Show was created.", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Failed to create show.", HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to create show", e);
        }
        return responseEntity;
    }

    @PutMapping("/show/update")
    ResponseEntity<String> updateShow(@RequestBody ShowDto showDto) {
        ResponseEntity<String> responseEntity;
        try {
//            this.showService.updateShow(showDto);
            responseEntity = new ResponseEntity<>("Show was updated.", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Failed to update show.", HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to update show", e);
        }
        return responseEntity;
    }

    @DeleteMapping("/show/delete")
    ResponseEntity<String> deleteShow(@RequestBody ShowDto showDto) {
        ResponseEntity<String> responseEntity;
        try {
//            this.showService.deleteShow(showDto);
            responseEntity = new ResponseEntity<>("Show was deleted.", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Failed to delete show.", HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to delete show", e);
        }
        return responseEntity;
    }
}

