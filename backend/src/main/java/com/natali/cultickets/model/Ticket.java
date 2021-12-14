package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private int id;
    @NotEmpty(message = "*Please provide ticket price")
    private int price;
    @NotEmpty(message = "*Please provide a seat")
    private Seat seat;
    @NotEmpty(message = "*Please provide a show")
    private TheaterHall theaterHall;
    private Sector sector;
    private Theatre theatre;
    private Show show;
    private TicketStatus ticketStatus;
}
