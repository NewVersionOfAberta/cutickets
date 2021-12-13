package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private int id;
    private Seat seat;
    private Show show;
    private TicketState ticketState;
}
