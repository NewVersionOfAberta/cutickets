package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    private int id;
    @NotEmpty(message = "*Please provide a theater name")
    private String name;
    @NotEmpty(message = "*Please provide a theater description")
    private String description;
    @NotEmpty(message = "*Please provide a theater address")
    private String address;
    private City city;
}
