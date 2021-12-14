package com.natali.cultickets.repository;

import com.natali.cultickets.model.TicketStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatusRepository {
    TicketStatus findByName(String name);
}
