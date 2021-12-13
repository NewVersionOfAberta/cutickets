package com.natali.cultickets.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class SchemeDto {
    private int id;
    private Date creationDate;
    private int rowsAmount;
    private int maxSeatsAmount;
    private TheaterHallDto theaterHall;
}
