//package com.natali.cultickets.service.impl;
//
//import com.natali.cultickets.dto.SchemeDto;
//import com.natali.cultickets.mapstruct.TheaterMapper;
//import com.natali.cultickets.model.Scheme;
//import com.natali.cultickets.repository.SchemeRepository;
//import com.natali.cultickets.service.SchemeService;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SchemeServiceImpl implements SchemeService {
//    private final SchemeRepository schemeRepository;
//    private final TheaterMapper theaterMapper;
//
//    @Autowired
//    public SchemeServiceImpl(SchemeRepository schemeRepository, TheaterMapper theaterMapper) {
//        this.schemeRepository = schemeRepository;
//        this.theaterMapper = theaterMapper;
//    }
//
//    public Optional<Scheme> findScheme(int schemeId) {
//        return this.schemeRepository.findById(schemeId);
//    }
//
//    @Override
//    public List<SchemeDto> findAll() {
//        List<Scheme> schemes = this.schemeRepository.findAll();
//        return schemes.stream()
//                .map(this.theaterMapper::schemeToSchemeDto)
//                .collect(Collectors.toList());
//    }
//}
