package com.natali.cultickets.repository;

import com.natali.cultickets.model.Role;
import com.natali.cultickets.model.TicketState;
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
    private final TicketStateRepository ticketStateRepository;

    @Autowired
    TicketStateRepositoryTest(Flyway flyway, TicketStateRepository ticketStateRepository) {
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

        TicketState soldState = this.ticketStateRepository.findByName(soldStateName);
        TicketState availableState = this.ticketStateRepository.findByName(soldStateName);
        TicketState unavailableState = this.ticketStateRepository.findByName(soldStateName);

        assertNotNull(soldState);
        assertNotNull(availableState);
        assertNotNull(unavailableState);
    }

    @Test
    void findByName_wrongStateName_null() {
        String testStateName = "2Sold";

        TicketState testState = this.ticketStateRepository.findByName(testStateName);

        assertNull(testState);
    }

}