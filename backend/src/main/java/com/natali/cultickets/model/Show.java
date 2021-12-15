package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    private int id;
    private Theatre theatre;
    @NotEmpty(message = "*Please provide show genre")
    private Set<Genre> genre;
    @NotEmpty(message = "*Please provide show name")
    private String name;
    @NotEmpty(message = "*Please provide show description")
    private String description;
    @NotEmpty(message = "*Please provide age rating for the show")
    private AgeRating ageRating;
    private String datetime;
}
