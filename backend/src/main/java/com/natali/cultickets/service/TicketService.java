package com.natali.cultickets.service;

import com.natali.cultickets.dto.TicketDto;
import com.natali.cultickets.model.User;
import java.util.List;

public interface TicketService {
//    TicketDto findTicketById(int id);

    List<TicketDto> findTicketsToShow(int showId);

//    List<TicketDto> findUserTickets(int userId);
//
//    void buyTicket(TicketDto ticketDto, User user);
//
//    void returnTicket(TicketDto ticketDto, User user);
}
