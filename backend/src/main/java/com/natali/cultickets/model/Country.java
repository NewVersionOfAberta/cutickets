package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private int id;
    @NotEmpty(message = "*Please provide a country name")
    private String name;
    private Set<City> cities;
}
