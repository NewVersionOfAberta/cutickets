package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.TheatreDto;
import com.natali.cultickets.mapstruct.TheaterMapper;
import com.natali.cultickets.model.Theatre;
import com.natali.cultickets.repository.TheatreRepository;
import com.natali.cultickets.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class TheaterServiceImpl implements TheaterService {
    private final TheatreRepository theaterRepository;
    private final TheaterMapper theaterMapper;

    @Autowired
    public TheaterServiceImpl(TheatreRepository theaterRepository, TheaterMapper theaterMapper) {
        this.theaterRepository = theaterRepository;
        this.theaterMapper = theaterMapper;
    }

    public TheatreDto findTheater(int theaterId) {
        Theatre theatre;
        try {
            theatre = this.theaterRepository.findById(theaterId).get();
        } catch (SQLException e) {
            theatre = null;
            e.printStackTrace();
        }
        return this.theaterMapper.theatreToTheatreDto(theatre);
    }

//    @Override
//    public List<TheaterDto> findAll() {
//        List<Theater> theaters = this.theaterRepository.findAll();
//        return theaters.stream()
//                .map(this.theaterMapper::theaterToTheaterDto)
//                .collect(Collectors.toList());
//    }
}
