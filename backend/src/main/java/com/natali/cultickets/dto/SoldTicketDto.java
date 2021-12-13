package com.natali.cultickets.dto;

import lombok.Data;

@Data
public class SoldTicketDto {
    private int id;
    private TicketDto ticket;
    private UserDto user;

}

