//package com.natali.cultickets.service.impl;
//
//import com.natali.cultickets.dto.ShowDto;
//import com.natali.cultickets.mapstruct.ShowMapper;
//import com.natali.cultickets.model.Scheme;
//import com.natali.cultickets.model.Show;
//import com.natali.cultickets.model.ShowState;
//import com.natali.cultickets.model.ShowType;
//import com.natali.cultickets.model.Theater;
//import com.natali.cultickets.repository.ShowRepository;
//import com.natali.cultickets.service.SchemeService;
//import com.natali.cultickets.service.ShowService;
//import com.natali.cultickets.service.ShowStateService;
//import com.natali.cultickets.service.ShowTypeService;
//import com.natali.cultickets.service.TheaterService;
//import com.natali.cultickets.service.exception.ServiceException;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ShowServiceImpl implements ShowService {
//    private final ShowRepository showRepository;
//    private final ShowMapper showMapper;
//    private final TheaterService theaterService;
//    private final SchemeService schemeService;
//    private final ShowStateService showStateService;
//    private final ShowTypeService showTypeService;
//
//    @Autowired
//    public ShowServiceImpl(ShowRepository showRepository, ShowMapper showMapper, TheaterService theaterService,
//                           SchemeService schemeService, ShowStateService showStateService, ShowTypeService showTypeService) {
//        this.showRepository = showRepository;
//        this.showMapper = showMapper;
//        this.theaterService = theaterService;
//        this.schemeService = schemeService;
//
//        this.showStateService = showStateService;
//        this.showTypeService = showTypeService;
//    }
//
//    public void updateShow(ShowDto showDto) {
//        this.showRepository.updateShow(showDto.getId(), showDto.getName(), showDto.getDescription());
//    }
//
//    public Show saveShow(ShowDto showDto) {
//        Show show = new Show();
//        Theater theater = this.theaterService.findTheater(showDto.getTheater().getId())
//                .orElseThrow(() -> new ServiceException("No theater was found with provided data"));
//        Scheme scheme = this.schemeService.findScheme(showDto.getScheme().getId())
//                .orElseThrow(() -> new ServiceException("No scheme was found with provided data"));
//        ShowState showState = this.showStateService.findShowState(showDto.getShowState().getId())
//                .orElseThrow(() -> new ServiceException("No show state was found with provided data"));
//        ShowType showType = this.showTypeService.findShowType(showDto.getShowType().getId())
//                .orElseThrow(() -> new ServiceException("No show type was found with provided data"));
//        show.setName(showDto.getName());
//        show.setDescription(showDto.getDescription());
//        show.setDatetime(showDto.getDatetime());
//        show.setScheme(scheme);
//        show.setShowState(showState);
//        show.setTheater(theater);
//        show.setShowType(showType);
//
//        return this.showRepository.save(show);
//
//    }
//
//    @Override
//    public List<ShowDto> findShows(int theaterId, int showTypeId) {
//        if (theaterId == 0 && showTypeId == 0) {
//            return getAllShows();
//        } else if (showTypeId == 0) {
//            return getShowsByTheater(theaterId);
//        } else if (theaterId == 0) {
//            return getShowsByType(showTypeId);
//        } else {
//            return getShowsByTheaterAndType(theaterId, showTypeId);
//        }
//    }
//
//    @Override
//    public void deleteShow(ShowDto showDto) {
//        Show show = this.showRepository.findById(showDto.getId())
//                .orElseThrow(() -> new ServiceException("There are no such show"));
//        this.showRepository.delete(show);
//    }
//
//    public List<ShowDto> getAllShows() {
//        List<Show> allShows = this.showRepository.findAll();
//        return allShows.stream()
//                .map(this.showMapper::showToShowDto)
//                .collect(Collectors.toList());
//    }
//
//    private List<ShowDto> getShowsByTheater(int theaterId) {
//        Theater theater = this.theaterService.findTheater(theaterId)
//                .orElseThrow(() -> new ServiceException("Cannot find theater with provided id"));
//        List<Show> showsByTheater = this.showRepository.findByTheater(theater);
//        return showsByTheater.stream()
//                .map(this.showMapper::showToShowDto)
//                .collect(Collectors.toList());
//    }
//
//    private List<ShowDto> getShowsByType(int typeId) {
//        ShowType type = this.showTypeService.findShowType(typeId)
//                .orElseThrow(() -> new ServiceException("Cannot find show type with provided id"));
//        List<Show> showsByType = this.showRepository.findByShowType(type);
//        return showsByType.stream()
//                .map(this.showMapper::showToShowDto)
//                .collect(Collectors.toList());
//    }
//
//    private List<ShowDto> getShowsByTheaterAndType(int theaterId, int typeId) {
//        Theater theater = this.theaterService.findTheater(theaterId)
//                .orElseThrow(() -> new ServiceException("Cannot find theater with provided id"));
//        ShowType type = this.showTypeService.findShowType(typeId)
//                .orElseThrow(() -> new ServiceException("Cannot find show type with provided id"));
//        List<Show> showsByTheaterAndType = this.showRepository.findByTheaterAndShowType(theater, type);
//        return showsByTheaterAndType.stream()
//                .map(this.showMapper::showToShowDto)
//                .collect(Collectors.toList());
//    }
//
//}
