//package com.natali.cultickets.repository;
//
//import com.natali.cultickets.model.SoldTicket;
//import com.natali.cultickets.model.Ticket;
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
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class SoldTicketRepositoryTest {
//    private final Flyway flyway;
//    private final TicketRepository ticketRepository;
//    private final SoldTicketRepository soldTicketRepository;
//
//    @Autowired
//    SoldTicketRepositoryTest(Flyway flyway, TicketRepository ticketRepository, SoldTicketRepository soldTicketRepository) {
//        this.flyway = flyway;
//        this.ticketRepository = ticketRepository;
//        this.soldTicketRepository = soldTicketRepository;
//    }
//
//    @AfterEach
//    void restoreDatabase() {
//        this.flyway.clean();
//        this.flyway.migrate();
//    }
//
//    @Test
//    void findByTicket_ticketId1_userId1() {
//        int testTicketId = 1;
//        int expectedUserId = 1, actualUserId;
//        Ticket ticket = this.ticketRepository.findById(testTicketId).orElseThrow();
//        Optional<SoldTicket> soldTicket = this.soldTicketRepository.findByTicket(ticket);
//
//        assertTrue(soldTicket.isPresent());
//
//        actualUserId = soldTicket.get().getUser().getId();
//
//        assertEquals(expectedUserId, actualUserId);
//    }
//
//    @Test
//    void findByTicket_ticketId10_null() {
//        int testTicketId = 10;
//        Ticket ticket = this.ticketRepository.findById(testTicketId).orElseThrow();
//        Optional<SoldTicket> soldTicket = this.soldTicketRepository.findByTicket(ticket);
//
//        assertFalse(soldTicket.isPresent());
//    }
//}