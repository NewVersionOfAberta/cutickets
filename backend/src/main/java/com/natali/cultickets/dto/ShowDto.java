package com.natali.cultickets.dto;

import java.sql.Date;

import com.natali.cultickets.model.AgeRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDto {
    private int id;
    private TheatreDto theater;
//    private SchemeDto scheme;
//    private ShowStateDto showState;
//    private ShowTypeDto showType;
    private String name;
    private String description;
    private AgeRating ageRating;
    private Date datetime;
}
