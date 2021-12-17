package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.TheatreDto;
import com.natali.cultickets.model.Theatre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheatreMapper {
    TheatreDto theatreToTheatreDto(Theatre theatre);

//    SchemeDto schemeToSchemeDto(Scheme scheme);
//
//    TheaterHallDto theaterHallToTheaterHallDto(TheaterHall theaterHall);

}
