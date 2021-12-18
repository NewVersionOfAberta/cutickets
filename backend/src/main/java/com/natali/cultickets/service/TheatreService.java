package com.natali.cultickets.service;

import com.natali.cultickets.dto.TheatreDto;

import java.util.List;

public interface TheatreService {
    TheatreDto findTheater(int userId, int theaterId);

    List<TheatreDto> findAll(int userId);
}
