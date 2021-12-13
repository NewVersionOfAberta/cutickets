package com.natali.cultickets.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    @NotEmpty(message = "*Please provide a role name")
    private String role = "USER";
}
