package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.TheatreDto;
import com.natali.cultickets.mapstruct.TheatreMapper;
import com.natali.cultickets.model.Theatre;
import com.natali.cultickets.repository.TheatreRepository;
import com.natali.cultickets.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreServiceImpl implements TheatreService {
    private final TheatreRepository theaterRepository;
    private final TheatreMapper theatreMapper;

    @Autowired
    public TheatreServiceImpl(TheatreRepository theatreRepository, TheatreMapper theaterMapper) {
        this.theaterRepository = theatreRepository;
        this.theatreMapper = theaterMapper;
    }

    public TheatreDto findTheater(int theaterId) {
        Theatre theatre;
        try {
            theatre = this.theaterRepository.findById(theaterId).get();
        } catch (SQLException e) {
            theatre = null;
            e.printStackTrace();
        }
        return this.theatreMapper.theatreToTheatreDto(theatre);
    }

//    @Override
//    public List<TheatreDto> findAll() {
//        return null;
//    }

    @Override
    public List<TheatreDto> findAll() {
        List<Theatre> theaters = null;
        try {
            theaters = this.theaterRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theaters.stream()
                .map(this.theatreMapper::theatreToTheatreDto)
                .collect(Collectors.toList());
    }
}
