package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketState {
    private int id;
    @NotEmpty(message = "*Please provide a ticket state name")
    private String name;
}
