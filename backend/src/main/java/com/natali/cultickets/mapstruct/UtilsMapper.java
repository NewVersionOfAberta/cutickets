package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.CityDto;
import com.natali.cultickets.dto.CountryDto;
import com.natali.cultickets.dto.ShowStateDto;
import com.natali.cultickets.dto.ShowTypeDto;
import com.natali.cultickets.model.City;
import com.natali.cultickets.model.Country;
import com.natali.cultickets.model.ShowState;
import com.natali.cultickets.model.ShowType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilsMapper {
    CityDto cityToCityDto(City city);

    CountryDto countryToCountryDto(Country country);

    ShowStateDto showStateToShowStateDto(ShowState showState);

    ShowTypeDto showTypeToShowTypeDto(ShowType showType);

}
