package com.natali.cultickets.service;

import com.natali.cultickets.dto.TheaterDto;
import com.natali.cultickets.model.Theater;
import java.util.List;
import java.util.Optional;

public interface TheaterService {
    Optional<Theater> findTheater(int theaterId);

    List<TheaterDto> findAll();
}
