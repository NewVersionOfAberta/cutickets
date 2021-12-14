//package com.natali.cultickets.controller;
//
//
//import com.natali.cultickets.dto.TicketDto;
//import com.natali.cultickets.dto.UserDto;
//import com.natali.cultickets.dto.UserGetDto;
//import com.natali.cultickets.mapstruct.UserMapper;
//import com.natali.cultickets.model.User;
//import com.natali.cultickets.service.TicketService;
//import com.natali.cultickets.service.UserService;
//import com.natali.cultickets.service.exception.ServiceException;
////import com.natali.cultickets.service.impl.UserServiceImpl;
//import java.security.Principal;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.natali.cultickets.service.impl.UserServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    private static final String MESSAGE_KEY = "message";
//
//    private final UserService userService;
//
//    private final TicketService ticketService;
//
//    private final UserMapper userMapper;
//
//
//    @Autowired
//    public UserController(
//            UserServiceImpl userService,
//            TicketService ticketService, UserMapper userMapper) {
//        this.userService = userService;
//        this.ticketService = ticketService;
//        this.userMapper = userMapper;
//    }
//
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
//
//    @GetMapping("/tickets")
//    public ResponseEntity<Map<String, Object>> getUserTickets(Principal principal) {
//        ResponseEntity<Map<String, Object>> responseEntity;
//        Map<String, Object> responseData = new HashMap<>();
//        try {
//            String userEmail = principal.getName();
////            User user = this.userService.findUserByEmail(userEmail).orElseThrow();
////            List<TicketDto> tickets = this.ticketService.findUserTickets(user.getId());
////            responseData.put("tickets", tickets);
//            responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
//        } catch (Exception e) {
//            responseData.put(MESSAGE_KEY, "Failed to find the user's tickets.");
//            responseEntity = new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
//            log.error("Failed to find the user's tickets.", e);
//        }
//        return responseEntity;
//    }
//
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
//
//    @PutMapping("/tickets/return/")
//    public ResponseEntity<Map<String, String>> returnTicket(@RequestBody TicketDto ticket, Principal principal) {
//        Map<String, String> response = new HashMap<>();
//        String userEmail = principal.getName();
////        User user = this.userService.findUserByEmail(userEmail).orElseThrow();
//        try {
////            this.ticketService.returnTicket(ticket, user);
//            response.put(MESSAGE_KEY, "Returned successfully");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (ServiceException e) {
//            response.put(MESSAGE_KEY, e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
