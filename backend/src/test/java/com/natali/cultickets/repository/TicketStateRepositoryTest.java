package com.natali.cultickets.repository;

import com.natali.cultickets.model.TicketStatus;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class TicketStateRepositoryTest {
    private final Flyway flyway;
    private final TicketStatusRepository ticketStateRepository;

    @Autowired
    TicketStateRepositoryTest(Flyway flyway, TicketStatusRepository ticketStateRepository) {
        this.flyway = flyway;
        this.ticketStateRepository = ticketStateRepository;
    }

    @AfterEach
    void restoreDatabase() {
        this.flyway.clean();
        this.flyway.migrate();
    }

    @Test
    void findByName_existsStateNames_present() {
        String soldStateName = "Sold", availableStateName = "Available", unavailableStateName = "Unavailable";

        TicketStatus soldState = this.ticketStateRepository.findByName(soldStateName);
        TicketStatus availableState = this.ticketStateRepository.findByName(soldStateName);
        TicketStatus unavailableState = this.ticketStateRepository.findByName(soldStateName);

        assertNotNull(soldState);
        assertNotNull(availableState);
        assertNotNull(unavailableState);
    }

    @Test
    void findByName_wrongStateName_null() {
        String testStateName = "2Sold";

        TicketStatus testState = this.ticketStateRepository.findByName(testStateName);

        assertNull(testState);
    }

}