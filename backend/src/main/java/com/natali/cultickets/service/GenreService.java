package com.natali.cultickets.service;

import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre findById(int genreId);

    List<GenreDto> findAll(int userId);
}
