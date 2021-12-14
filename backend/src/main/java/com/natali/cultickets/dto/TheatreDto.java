package com.natali.cultickets.dto;

import lombok.Data;

@Data
public class TheatreDto {
    private int id;
    private String name;
    private String description;
    private TheatreHallDto hall;
    private int address_id;
    private CityDto city;
    private RegionDto region;
    private CountryDto country;
}
