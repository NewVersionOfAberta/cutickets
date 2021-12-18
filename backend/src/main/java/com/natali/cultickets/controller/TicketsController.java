package com.natali.cultickets.controller;

import com.natali.cultickets.dto.TicketDto;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.service.TicketService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.natali.cultickets.service.UserService;
import com.natali.cultickets.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/tickets/")
public class TicketsController {
    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public TicketsController(TicketService ticketService, UserServiceImpl userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping("/scheduledShow/{ssId}")
    ResponseEntity<Map<String, Object>> getTicketsToTheShow(@PathVariable int ssId, Principal principal) {
        String login = principal.getName();
        AuthInfo authInfo = userService.findByLogin(login);
        int userId = authInfo.getUserId();

        ResponseEntity<Map<String, Object>> responseEntity;
        Map<String, Object> responseData = new HashMap<>();
        try {
            List<TicketDto> tickets = this.ticketService.findTicketsToShow(userId, ssId);
            responseData.put("tickets", tickets);
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.put("message", "Failed to get tickets.");
            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to get tickets", e);
        }
        return responseEntity;
    }

    @GetMapping("/scheduledShow/{userId}/{ticketId}")
    ResponseEntity<Void> buyTicket(@PathVariable int userId, @PathVariable int ticketId) {
        try {
            this.ticketService.buyTicket(userId, ticketId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to get tickets", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
