package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.model.Show;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShowMapper {
    ShowDto showToShowDto(Show show);
}
