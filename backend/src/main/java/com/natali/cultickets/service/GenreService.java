package com.natali.cultickets.service;

import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findShowType(int showTypeId);

    List<GenreDto> findAll(int userId);
}
