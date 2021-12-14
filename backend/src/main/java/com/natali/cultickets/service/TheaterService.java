package com.natali.cultickets.service;

import com.natali.cultickets.dto.TheatreDto;
import com.natali.cultickets.model.Theatre;

import java.util.Optional;

public interface TheaterService {
    TheatreDto findTheater(int theaterId);

//    List<TheaterDto> findAll();
}
