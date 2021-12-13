package com.natali.cultickets.dto;

import lombok.Data;

@Data
public class SeatDto {

    private int id;
    private int row;
    private int number;
    private PriceDto price;
}
