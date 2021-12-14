package com.natali.cultickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    private int id;
    @NotEmpty(message = "*Please provide the theatre for the show")
    private Theatre theatre;
    @NotEmpty(message = "*Please provide show genre")
    private Set<Genre> genre;
    @NotEmpty(message = "*Please provide show name")
    private String name;
    @NotEmpty(message = "*Please provide show description")
    private String description;
    @NotEmpty(message = "*Please provide age rating for the show")
    private AgeRating ageRating;
//    @NotEmpty(message = "*Please provide show time")
    private Date datetime;
}
