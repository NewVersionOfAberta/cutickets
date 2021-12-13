//package com.natali.cultickets.service.impl;
//
//import com.natali.cultickets.dto.TheaterDto;
//import com.natali.cultickets.mapstruct.TheaterMapper;
//import com.natali.cultickets.model.Theater;
//import com.natali.cultickets.repository.TheaterRepository;
//import com.natali.cultickets.service.TheaterService;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TheaterServiceImpl implements TheaterService {
//    private final TheaterRepository theaterRepository;
//    private final TheaterMapper theaterMapper;
//
//    @Autowired
//    public TheaterServiceImpl(TheaterRepository theaterRepository, TheaterMapper theaterMapper) {
//        this.theaterRepository = theaterRepository;
//        this.theaterMapper = theaterMapper;
//    }
//
//    public Optional<Theater> findTheater(int theaterId) {
//        return this.theaterRepository.findById(theaterId);
//    }
//
//    @Override
//    public List<TheaterDto> findAll() {
//        List<Theater> theaters = this.theaterRepository.findAll();
//        return theaters.stream()
//                .map(this.theaterMapper::theaterToTheaterDto)
//                .collect(Collectors.toList());
//    }
//}
