package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private int id;
    @Min(value = 0, message = "Price must be positive number")
    private int price;
    private String color;
}
