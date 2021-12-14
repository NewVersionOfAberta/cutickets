package com.natali.cultickets.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import com.natali.cultickets.model.AgeRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDto {
    private int id;
    private TheatreDto theatre;
    private Set<GenreDto> genre;
    private PriceSchemeDto priceScheme;
    private String name;
    private String description;
    private AgeRating ageRating;
    private String datetime;
}
