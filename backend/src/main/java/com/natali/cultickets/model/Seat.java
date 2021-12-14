package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private int id;
    @Min(value = 1, message = "Row numbers have to start from 1.")
    private int row;
    @Min(value = 1, message = "Seats numbers have to start from 1.")
    private int number;
    private int sectorId;
}
