package com.natali.cultickets.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SoldTicketDto {
    private int id;
    private TicketDto ticket;
    private UserDto user;
    private Date time;
}

