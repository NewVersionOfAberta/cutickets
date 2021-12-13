package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterHall {
    private int id;
    private Theater theater;
    @NotEmpty(message = "*Please provide a theater hall name")
    private String name;
}
