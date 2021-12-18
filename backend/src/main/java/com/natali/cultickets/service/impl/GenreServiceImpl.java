package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.mapstruct.UtilsMapper;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.repository.GenreRepository;
import com.natali.cultickets.repository.JournalRepository;
import com.natali.cultickets.service.GenreService;
import com.natali.cultickets.service.detailsService.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final JournalRepository journalRepository;
    private final UtilsMapper utilsMapper;
    private final UserDetailsService userDetailsService;

    @Autowired
    public GenreServiceImpl(GenreRepository showTypeRepository,
                            UtilsMapper utilsMapper,
                            JournalRepository journalRepository,
                            UserDetailsServiceImpl userDetailsService) {
        this.genreRepository = showTypeRepository;
        this.utilsMapper = utilsMapper;
        this.journalRepository = journalRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Optional<Genre> findShowType(int showTypeId) {
        return this.genreRepository.findById(showTypeId);
    }

    @Override
    public List<GenreDto> findAll(int userId) {
        List<Genre> showTypes = this.genreRepository.findAll();
        journalRepository.write(userId, "genre", null, null, JournalRepository.Operation.READ);
        return showTypes.stream()
                .map(this.utilsMapper::genreToGenreDto)
                .collect(Collectors.toList());
    }
}
