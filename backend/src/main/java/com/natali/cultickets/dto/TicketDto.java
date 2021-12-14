package com.natali.cultickets.dto;

import com.natali.cultickets.model.Sector;
import com.natali.cultickets.model.TheaterHall;
import com.natali.cultickets.model.Theatre;
import lombok.Data;

@Data
public class TicketDto {
    private int id;
    private int price;
    private SeatDto seat;
    private TheaterHall theaterHall;
    private Sector sector;
    private Theatre theatre;
    private ShowDto show;
    private TicketStatusDto ticketStatus;
}
