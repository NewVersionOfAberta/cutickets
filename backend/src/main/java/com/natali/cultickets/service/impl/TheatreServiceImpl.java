package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.TheatreDto;
import com.natali.cultickets.mapstruct.TheatreMapper;
import com.natali.cultickets.model.Theatre;
import com.natali.cultickets.repository.JournalRepository;
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
    private final JournalRepository journalRepository;

    @Autowired

    public TheatreServiceImpl(TheatreRepository theatreRepository,
                              TheatreMapper theaterMapper,
                              JournalRepository journalRepository) {
        this.theaterRepository = theatreRepository;
        this.theatreMapper = theaterMapper;
        this.journalRepository = journalRepository;
    }

    public TheatreDto findTheater(int userId, int theaterId) {
        Theatre theatre;
        try {
            journalRepository.write(userId, "theatre", "t_id",
                    String.valueOf(theaterId), JournalRepository.Operation.READ);
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
    public List<TheatreDto> findAll(int userId) {
        List<Theatre> theaters = null;
        try {
            journalRepository.write(userId, "theatre", null,
                    null, JournalRepository.Operation.READ);
            theaters = this.theaterRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theaters.stream()
                .map(this.theatreMapper::theatreToTheatreDto)
                .collect(Collectors.toList());
    }
}
