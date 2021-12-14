package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.CityDto;
import com.natali.cultickets.dto.CountryDto;
import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.model.City;
import com.natali.cultickets.model.Country;
import com.natali.cultickets.model.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilsMapper {
    CityDto cityToCityDto(City city);

    CountryDto countryToCountryDto(Country country);

    GenreDto genreToGenreDto(Genre genre);
}
