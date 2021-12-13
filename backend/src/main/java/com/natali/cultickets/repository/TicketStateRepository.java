package com.natali.cultickets.repository;

import com.natali.cultickets.model.TicketState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStateRepository {
    TicketState findByName(String name);
}
