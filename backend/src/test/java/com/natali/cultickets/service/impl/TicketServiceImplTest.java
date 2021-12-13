//package com.natali.cultickets.service.impl;
//
//import com.natali.cultickets.dto.TicketDto;
//import com.natali.cultickets.model.User;
//import com.natali.cultickets.repository.UserRepository;
//import com.natali.cultickets.service.TicketService;
//import com.natali.cultickets.service.exception.ServiceException;
//import java.util.List;
//import org.flywaydb.core.Flyway;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class TicketServiceImplTest {
//
//    private final Flyway flyway;
//    private final TicketService ticketService;
//    private final UserRepository userRepository;
//
//    @Autowired
//    TicketServiceImplTest(Flyway flyway,
//                          TicketService ticketService,
//                          UserRepository userRepository) {
//        this.flyway = flyway;
//        this.ticketService = ticketService;
//        this.userRepository = userRepository;
//    }
//
//    @AfterEach
//    void restoreDatabase() {
//        this.flyway.clean();
//        this.flyway.migrate();
//    }
//
//    @Test
//    void findTicketsToShow_showId1_14tickets() {
//        int expectedTicketsAmount = 14;
//        int actualTicketsAmount;
//        int testShowId = 1;
//        List<TicketDto> ticketsToShow = this.ticketService.findTicketsToShow(testShowId);
//        actualTicketsAmount = ticketsToShow.size();
//
//        assertEquals(expectedTicketsAmount, actualTicketsAmount);
//    }
//
//    @Test
//    void findUserTickets_user1_2tickets() {
//        int expectedTicketsAmount = 2;
//        int actualTicketsAmount;
//        int testUserId = 1;
//
//        List<TicketDto> userTickets = this.ticketService.findUserTickets(testUserId);
//        actualTicketsAmount = userTickets.size();
//
//        assertEquals(expectedTicketsAmount, actualTicketsAmount);
//    }
//
//    @Test
//    void buyTicket_id3userId1_user1have3tickets() {
//        int expectedTicketsAmount = 3;
//        int actualTicketsAmount;
//        int testUserId = 1, testTicketId = 3;
//
//        TicketDto ticketDto = this.ticketService.findTicketById(testTicketId);
//        User user = this.userRepository.findById(testUserId).orElseThrow();
//
//        this.ticketService.buyTicket(ticketDto, user);
//        List<TicketDto> userTickets = this.ticketService.findUserTickets(testUserId);
//        actualTicketsAmount = userTickets.size();
//
//        assertEquals(expectedTicketsAmount, actualTicketsAmount);
//
//    }
//
//    @Test
//    void buyTicket_tryBuyTwice_ServiceException() {
//        int testUserId = 1, testTicketId = 3;
//
//        TicketDto ticketDto = this.ticketService.findTicketById(testTicketId);
//        User user = this.userRepository.findById(testUserId).orElseThrow();
//
//        this.ticketService.buyTicket(ticketDto, user);
//        assertThrows(ServiceException.class, () -> this.ticketService.buyTicket(ticketDto, user));
//    }
//
//    @Test
//    void returnTicket_id1userId1_user1have2tickets() {
//        int expectedTicketsAmount = 1;
//        int actualTicketsAmount;
//        int testUserId = 1, testTicketId = 1;
//
//        TicketDto ticketDto = this.ticketService.findTicketById(testTicketId);
//        User user = this.userRepository.findById(testUserId).orElseThrow();
//
//        this.ticketService.returnTicket(ticketDto, user);
//        List<TicketDto> userTickets = this.ticketService.findUserTickets(testUserId);
//        actualTicketsAmount = userTickets.size();
//
//        assertEquals(expectedTicketsAmount, actualTicketsAmount);
//    }
//
//    @Test
//    void returnTicket_tryToReturnTwice_ServiceException() {
//        int testUserId = 1, testTicketId = 1;
//
//        TicketDto ticketDto = this.ticketService.findTicketById(testTicketId);
//        User user = this.userRepository.findById(testUserId).orElseThrow();
//
//        this.ticketService.returnTicket(ticketDto, user);
//        assertThrows(ServiceException.class, () -> this.ticketService.returnTicket(ticketDto, user));
//    }
//}