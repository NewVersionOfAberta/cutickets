package com.natali.cultickets.dto;

import lombok.Data;

@Data
public class TheaterDto {
    private int id;
    private String name;
    private String description;
    private String address;
    private CityDto city;
}
