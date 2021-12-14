package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.mapstruct.UtilsMapper;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.repository.GenreRepository;
import com.natali.cultickets.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final UtilsMapper utilsMapper;

    @Autowired
    public GenreServiceImpl(GenreRepository showTypeRepository, UtilsMapper utilsMapper) {
        this.genreRepository = showTypeRepository;
        this.utilsMapper = utilsMapper;
    }

    @Override
    public Optional<Genre> findShowType(int showTypeId) {
        return this.genreRepository.findById(showTypeId);
    }

    @Override
    public List<GenreDto> findAll() {
        List<Genre> showTypes = this.genreRepository.findAll();
        return showTypes.stream()
                .map(this.utilsMapper::genreToGenreDto)
                .collect(Collectors.toList());
    }
}
