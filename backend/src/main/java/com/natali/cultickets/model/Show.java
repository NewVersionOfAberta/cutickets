package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    private int id;
    private Theater theater;
    private ShowState showState;
    private ShowType showType;
    @NotEmpty(message = "*Please provide a show name")
    private String name;
    @NotEmpty(message = "*Please provide a show description")
    private String description;
    private Date datetime;
}
