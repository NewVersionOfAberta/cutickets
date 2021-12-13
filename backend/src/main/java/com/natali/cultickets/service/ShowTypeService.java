package com.natali.cultickets.service;

import com.natali.cultickets.dto.ShowTypeDto;
import com.natali.cultickets.model.ShowType;
import java.util.List;
import java.util.Optional;

public interface ShowTypeService {
    Optional<ShowType> findShowType(int showTypeId);

    List<ShowTypeDto> findAll();
}
