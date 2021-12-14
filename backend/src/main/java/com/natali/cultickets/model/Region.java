package com.natali.cultickets.model;

import com.natali.cultickets.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    private int id;
    @NotEmpty(message = "*Please provide a region name")
    private String name;
    private CountryDto country;
}
