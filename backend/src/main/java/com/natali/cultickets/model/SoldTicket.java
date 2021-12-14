package com.natali.cultickets.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoldTicket {
    private int id;
    @NotEmpty(message = "*Please define the ticket that's been sold")
    private Ticket ticket;
    @NotEmpty(message = "*Please provide the user who bought the ticket")
    private User user;
    @NotEmpty(message = "*Please provide the time the ticket's been sold")
    private Date datetime;
}
