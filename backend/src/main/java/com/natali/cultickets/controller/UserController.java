package com.natali.cultickets.controller;


import com.natali.cultickets.dto.*;
import com.natali.cultickets.mapstruct.UserMapper;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.model.User;
import com.natali.cultickets.service.TicketService;
import com.natali.cultickets.service.UserService;
import com.natali.cultickets.service.exception.ServiceException;
//import com.natali.cultickets.service.impl.UserServiceImpl;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.natali.cultickets.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String MESSAGE_KEY = "message";

    private final UserService userService;

    private final TicketService ticketService;

    private final UserMapper userMapper;


    @Autowired
    public UserController(
            UserServiceImpl userService,
            TicketService ticketService, UserMapper userMapper) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.userMapper = userMapper;
    }

//    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
//    public UserDto home() {
//        return new UserDto();
//    }
//
//    @RequestMapping(value = "/profile", method = RequestMethod.GET)
//    public ResponseEntity<Map<String, Object>> home(Principal principal) {
//        ResponseEntity<Map<String, Object>> responseEntity;
//        Map<String, Object> responseData = new HashMap<>();
//        try {
//            String userEmail = principal.getName();
////            User user = this.userService.findUserByEmail(userEmail).orElse(null);
////            UserGetDto userGetDto = userMapper.userToUserGetDto(user);
////            responseData.put("user", userGetDto);
//            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
//        } catch (Exception e) {
//            responseData.put(MESSAGE_KEY, "Failed to find the user.");
//            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
//            log.error("Failed to find the user.", e);
//        }
//        return responseEntity;
//    }

    @GetMapping("/tickets/{userId}")
    public ResponseEntity<Map<String, Object>> getUserTickets(@PathVariable int userId) {
        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            List<TicketDto> tickets = this.ticketService.getUserTickets(userId);
            responseData.put("tickets", tickets);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put(MESSAGE_KEY, "Failed to find user's tickets.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to find the user's tickets.", e);
        }
        return responseEntity;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable int userId) {
        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            UserDto userInfo = this.userService.getUserInfo(userId);
            responseData.put("info", userInfo);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put(MESSAGE_KEY, "Failed to find user's tickets.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to find the user's tickets.", e);
        }
        return responseEntity;
    }

    @GetMapping("/{userId}/stats")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable int userId) {
        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            List<GenreDto> userInfo = this.userService.getPreferableGenres(userId);
            List<ExpensesDto> exps = this.userService.getUserExpenses(userId);
            responseData.put("info", userInfo);
            responseData.put("exps", exps);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put(MESSAGE_KEY, "Failed to find user's tickets.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to find the user's tickets.", e);
        }
        return responseEntity;
    }

//    @PutMapping("/tickets/purchase/")
//    public ResponseEntity<Map<String, String>> buyTicket(@RequestBody List<TicketDto> tickets, Principal principal) {
//        Map<String, String> response = new HashMap<>();
//        User user;
//        try {
//            String userEmail = principal.getName();
////            user = this.userService.findUserByEmail(userEmail).orElseThrow();
//        } catch (Exception e) {
//            response.put(MESSAGE_KEY, "Something went wrong");
//            log.error("Failed to find the user", e);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
////        String stringMessage = buyAllTickets(tickets, user);
////        if (!stringMessage.isEmpty()) {
////            response.put(MESSAGE_KEY, stringMessage);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////        } else {
////            response.put(MESSAGE_KEY, "Tickets bought successfully");
////            return new ResponseEntity<>(response, HttpStatus.OK);
////        }
//    }
//
//    private String buyAllTickets(List<TicketDto> tickets, User user) {
//        StringBuilder message = new StringBuilder();
//        tickets.forEach((e) -> {
//            try {
////                this.ticketService.buyTicket(e, user);
//            } catch (ServiceException ex) {
//                message.append("Failed to buy ticket. Place: ")
//                        .append(e.getSeat().getNumber())
//                        .append(" in row: ")
//                        .append(e.getSeat().getRow())
//                        .append(ex.getMessage());
//            }
//        });
//        return message.toString();
//    }

    @GetMapping("/tickets/return/user={userId}&ticket={ticketId}")
    public ResponseEntity<Void> returnTicket(@PathVariable int userId, @PathVariable int ticketId) {
        try {
            this.ticketService.returnTicket(userId, ticketId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
