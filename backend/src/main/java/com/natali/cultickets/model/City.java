package com.natali.cultickets.model;

import com.natali.cultickets.dto.RegionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private int id;
    @NotEmpty(message = "*Please provide a city name")
    private String name;
    private RegionDto region;
}
