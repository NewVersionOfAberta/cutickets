//package com.natali.cultickets.repository;
//
//import com.natali.cultickets.model.Ticket;
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
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class TicketRepositoryTest {
//    private final Flyway flyway;
//    private final TicketRepository ticketRepository;
//
//    @Autowired
//    TicketRepositoryTest(Flyway flyway, TicketRepository ticketRepository) {
//        this.flyway = flyway;
//        this.ticketRepository = ticketRepository;
//    }
//
//    @AfterEach
//    void restoreDatabase() {
//        this.flyway.clean();
//        this.flyway.migrate();
//    }
//
//    @Test
//    void findByShowId_id1_exists() {
//        int testShowId = 1;
//
//        Optional<Ticket> ticket = this.ticketRepository.findById(testShowId);
//
//        assertTrue(ticket.isPresent());
//    }
//
//    @Test
//    void findByUserId_userId1_2() {
//        int testUserId = 1;
//        int expectedTicketsAmount = 2, actualTicketsAmount;
//
//        List<Ticket> tickets = this.ticketRepository.findByUserId(testUserId);
//        actualTicketsAmount = tickets.size();
//
//        assertEquals(expectedTicketsAmount, actualTicketsAmount);
//    }
//
//    @Test
//    void findByUserId_userId2_0() {
//        int testUserId = 2;
//        int expectedTicketsAmount = 0, actualTicketsAmount;
//
//        List<Ticket> tickets = this.ticketRepository.findByUserId(testUserId);
//        actualTicketsAmount = tickets.size();
//
//        assertEquals(expectedTicketsAmount, actualTicketsAmount);
//    }
//
//}