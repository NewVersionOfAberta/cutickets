//package com.natali.cultickets.service.impl;
//
//import com.natali.cultickets.dto.ShowTypeDto;
//import com.natali.cultickets.mapstruct.UtilsMapper;
//import com.natali.cultickets.model.ShowType;
//import com.natali.cultickets.repository.ShowTypeRepository;
//import com.natali.cultickets.service.ShowTypeService;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ShowTypeServiceImpl implements ShowTypeService {
//    private final ShowTypeRepository showTypeRepository;
//    private final UtilsMapper utilsMapper;
//
//    @Autowired
//    public ShowTypeServiceImpl(ShowTypeRepository showTypeRepository, UtilsMapper utilsMapper) {
//        this.showTypeRepository = showTypeRepository;
//        this.utilsMapper = utilsMapper;
//    }
//
//    @Override
//    public Optional<ShowType> findShowType(int showTypeId) {
//        return this.showTypeRepository.findById(showTypeId);
//    }
//
//    @Override
//    public List<ShowTypeDto> findAll() {
//        List<ShowType> showTypes = this.showTypeRepository.findAll();
//        return showTypes.stream()
//                .map(this.utilsMapper::showTypeToShowTypeDto)
//                .collect(Collectors.toList());
//    }
//}
