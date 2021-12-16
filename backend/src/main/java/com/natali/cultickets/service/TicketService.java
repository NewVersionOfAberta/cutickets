package com.natali.cultickets.service;

import com.natali.cultickets.dto.TicketDto;
import com.natali.cultickets.model.User;
import java.util.List;

public interface TicketService {
//    TicketDto findTicketById(int id);

    List<TicketDto> findTicketsToShow(int showId);

    List<TicketDto> getUserTickets(int userId);

    void buyTicket(int userId, int ticketId);

    void returnTicket(int userId, int ticketId);
}
