package com.natali.cultickets.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldTicket {
    private int id;
    private Ticket ticket;
    private User user;
    private Date datetime;
}
