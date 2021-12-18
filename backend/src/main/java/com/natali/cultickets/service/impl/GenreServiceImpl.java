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

import java.sql.SQLException;
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
    public Genre findById(int genreId) {
        try {
            return this.genreRepository.findById(genreId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GenreDto> findAll(int userId) {
        List<Genre> showTypes = null;
        try {
            journalRepository.write(userId, "genre", null, null, JournalRepository.Operation.READ);
            showTypes = this.genreRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showTypes.stream()
                .map(this.utilsMapper::genreToGenreDto)
                .collect(Collectors.toList());
    }
}
