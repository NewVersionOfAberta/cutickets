package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.dto.TheatreDto;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.model.Theatre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto genreToGenreDto(Genre genre);
}