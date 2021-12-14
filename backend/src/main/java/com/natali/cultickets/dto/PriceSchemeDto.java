package com.natali.cultickets.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class PriceSchemeDto {
    private int id;
    private List<SeatDto> seatList;
    private List<Integer> priceList;
}
