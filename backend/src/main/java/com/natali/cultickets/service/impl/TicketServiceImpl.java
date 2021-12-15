package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.TicketDto;
import com.natali.cultickets.mapstruct.TicketMapper;
import com.natali.cultickets.model.Ticket;
import com.natali.cultickets.repository.TicketRepository;
import com.natali.cultickets.service.TicketService;
import com.natali.cultickets.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private static final String AVAILABLE_STATE = "Available";
    private static final String SOLD_STATE = "Sold";
    private static final Set<String> NOT_ACTIVE_TICKET_STATES = new HashSet<>(List.of(SOLD_STATE, "Unavailable"));

//    @PersistenceContext
//    private EntityManager entityManager;

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
//    private final SoldTicketRepository soldTicketRepository;
//    private final TicketStateRepository ticketStateRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper
//            , SoldTicketRepository soldTicketRepository, TicketStateRepository ticketStateRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
//        this.soldTicketRepository = soldTicketRepository;
//        this.ticketStateRepository = ticketStateRepository;
    }

//    @Override
//    public TicketDto findTicketById(int id) {
//        Ticket ticket = this.ticketRepository.findById(id)
//                .orElseThrow(() -> new ServiceException("Cannot find ticket with provided id"));
//        return this.ticketMapper.ticketToTicketDto(ticket);
//    }

    @Override
    public List<TicketDto> findTicketsToShow(int showId) {
        List<Ticket> ticketsByShow = null;
        try {
            ticketsByShow = this.ticketRepository.findByScheduledShowId(showId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketsByShow.stream()
                .map(this.ticketMapper::ticketToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> getUserTickets(int userId) {
        List<Ticket> ticketsByUserId = null;
        try {
            ticketsByUserId = this.ticketRepository.getUserTickets(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketsByUserId.stream()
                .map(this.ticketMapper::ticketToTicketDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void buyTicket(int userId, int ticketId) {
        try {
            this.ticketRepository.buyTicket(userId, ticketId);
        } catch (SQLException e) {
            throw new ServiceException("The ticket is unavailable.");
        }
    }



//
//    @Override
//    @Transactional
//    public void returnTicket(TicketDto ticketDto, User user) {
//        Ticket ticket = this.ticketRepository.findById(ticketDto.getId())
//                .orElseThrow(() -> new ServiceException("Cannot find ticket with provided id"));
//        if (!ticket.getTicketState().getName().equals(SOLD_STATE)) {
//            throw new ServiceException("Ticket is not sold");
//        }
//        SoldTicket soldTicket = this.soldTicketRepository.findByTicket(ticket)
//                .orElseThrow(() -> new ServiceException("Cannot find this ticket in sold tickets"));
//        if (!soldTicket.getUser().equals(user)) {
//            throw new ServiceException("This user isn't own ticket");
//        }
//        TicketState soldTicketState = this.ticketStateRepository.findByName(AVAILABLE_STATE);
//        ticket.setTicketState(soldTicketState);
//        this.ticketRepository.save(ticket);
//        this.soldTicketRepository.delete(soldTicket);
//
//    }


}
