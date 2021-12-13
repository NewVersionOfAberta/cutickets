package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.ShowStateDto;
import com.natali.cultickets.mapstruct.UtilsMapper;
import com.natali.cultickets.model.ShowState;
import com.natali.cultickets.repository.ShowStateRepository;
import com.natali.cultickets.service.ShowStateService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ShowStateServiceImpl implements ShowStateService {
//    private final ShowStateRepository showStateRepository;
//    private final UtilsMapper utilsMapper;

//    @Autowired
//    public ShowStateServiceImpl(ShowStateRepository showStateRepository, UtilsMapper utilsMapper) {
//        this.showStateRepository = showStateRepository;
//        this.utilsMapper = utilsMapper;
//    }
    @Override
    public Optional<ShowState> findShowState(int showId) {
//        return this.showStateRepository.findById(showId);
        return Optional.empty();
    }

    @Override
    public List<ShowStateDto> findAll() {
//        List<ShowState> showStates = this.showStateRepository.findAll();
//        return showStates.stream()
//                .map(this.utilsMapper::showStateToShowStateDto)
//                .collect(Collectors.toList());
        return Collections.EMPTY_LIST;
    }
}
