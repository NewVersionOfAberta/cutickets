//package com.natali.cultickets.service.impl;
//
//import com.natali.cultickets.dto.TicketDto;
//import com.natali.cultickets.mapstruct.TicketMapper;
//import com.natali.cultickets.model.SoldTicket;
//import com.natali.cultickets.model.Ticket;
//import com.natali.cultickets.model.TicketState;
//import com.natali.cultickets.model.User;
//import com.natali.cultickets.repository.SoldTicketRepository;
//import com.natali.cultickets.repository.TicketRepository;
//import com.natali.cultickets.repository.TicketStateRepository;
//import com.natali.cultickets.service.TicketService;
//import com.natali.cultickets.service.exception.ServiceException;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//import javax.persistence.EntityManager;
//import javax.persistence.LockModeType;
//import javax.persistence.PersistenceContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class TicketServiceImpl implements TicketService {
//    private static final String AVAILABLE_STATE = "Available";
//    private static final String SOLD_STATE = "Sold";
//    private static final Set<String> NOT_ACTIVE_TICKET_STATES = new HashSet<>(List.of(SOLD_STATE, "Unavailable"));
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final TicketRepository ticketRepository;
//    private final TicketMapper ticketMapper;
//    private final SoldTicketRepository soldTicketRepository;
//    private final TicketStateRepository ticketStateRepository;
//
//    @Autowired
//    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper, SoldTicketRepository soldTicketRepository, TicketStateRepository ticketStateRepository) {
//        this.ticketRepository = ticketRepository;
//        this.ticketMapper = ticketMapper;
//        this.soldTicketRepository = soldTicketRepository;
//        this.ticketStateRepository = ticketStateRepository;
//    }
//
//    @Override
//    public TicketDto findTicketById(int id) {
//        Ticket ticket = this.ticketRepository.findById(id)
//                .orElseThrow(() -> new ServiceException("Cannot find ticket with provided id"));
//        return this.ticketMapper.ticketToTicketDto(ticket);
//    }
//
//    @Override
//    public List<TicketDto> findTicketsToShow(int showId) {
//        List<Ticket> ticketsByShow = this.ticketRepository.findByShowId(showId);
//        return ticketsByShow.stream()
//                .map(this.ticketMapper::ticketToTicketDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TicketDto> findUserTickets(int userId) {
//        List<Ticket> ticketsByUserId = this.ticketRepository.findByUserId(userId);
//        return ticketsByUserId.stream()
//                .map(this.ticketMapper::ticketToTicketDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public void buyTicket(TicketDto ticketDto, User user) {
//        Ticket ticket = this.entityManager.find(Ticket.class, ticketDto.getId(), LockModeType.PESSIMISTIC_WRITE);
//        String ticketStateName = ticket.getTicketState().getName();
//        if (NOT_ACTIVE_TICKET_STATES.contains(ticketStateName)) {
//            throw new ServiceException("Ticket unavailable. It must have state 'Active', but has state: " + ticketStateName);
//        }
//
//        TicketState soldTicketState = this.ticketStateRepository.findByName(SOLD_STATE);
//        ticket.setTicketState(soldTicketState);
//
//        SoldTicket soldTicket = new SoldTicket();
//        soldTicket.setTicket(ticket);
//        soldTicket.setDatetime(Date.valueOf(LocalDate.now()));
//        soldTicket.setUser(user);
//
//        this.ticketRepository.save(ticket);
//        this.soldTicketRepository.save(soldTicket);
//    }
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
//
//
//}
