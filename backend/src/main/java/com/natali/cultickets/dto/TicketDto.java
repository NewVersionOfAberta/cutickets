package com.natali.cultickets.dto;

import lombok.Data;

@Data
public class TicketDto {
    private int id;
    private SeatDto seat;
    private ShowDto show;
    private TicketStateDto ticketState;
}
