package com.natali.cultickets.dto;

import lombok.Data;

@Data
public class TheatreHallDto {
    private int id;
    private TheatreDto theatre;
    private String name;
}
