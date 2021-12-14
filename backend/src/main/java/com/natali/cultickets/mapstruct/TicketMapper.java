package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.TicketDto;
import com.natali.cultickets.model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
//    TicketStateDto ticketStateDtoToTicketState(TicketState ticketState);
//
    TicketDto ticketToTicketDto(Ticket ticket);
}
