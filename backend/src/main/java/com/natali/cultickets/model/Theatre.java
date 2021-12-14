package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {
    private int id;
    @NotEmpty(message = "*Please provide a theater name")
    private String name;
    @NotEmpty(message = "*Please provide a theater description")
    private String description;
//    @NotEmpty(message = "*Please provide a theater address")
    private int address_id;
    private City city;
    private Region region;
    private Country country;
}
