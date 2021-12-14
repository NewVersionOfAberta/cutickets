package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.mapstruct.ShowMapper;
import com.natali.cultickets.model.Show;
import com.natali.cultickets.repository.ShowRepository;
import com.natali.cultickets.service.GenreService;
import com.natali.cultickets.service.ShowService;
import com.natali.cultickets.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final ShowMapper showMapper;
    private final TheaterService theaterService;
    private final GenreService genreService;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository, ShowMapper showMapper, TheaterService theaterService,
                           GenreService genreService) {
        this.showRepository = showRepository;
        this.showMapper = showMapper;
        this.theaterService = theaterService;
        this.genreService = genreService;
    }

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

    @Override
    public List<ShowDto> findShows(int theaterId, int showTypeId, int userId) {
        if (theaterId == 0 && showTypeId == 0 && userId == 0) {
            return getAllShows();
        }
        else if(theaterId == 0 && showTypeId == 0) {
            return findSuitableForUser(userId);
        }
        return null;
//        else if (showTypeId == 0) {
//            return getShowsByTheater(theaterId);
//        } else if (theaterId == 0) {
//            return getShowsByType(showTypeId);
//        } else {
//            return getShowsByTheaterAndType(theaterId, showTypeId);
//        }
    }

//    @Override
//    public void deleteShow(ShowDto showDto) {
//        Show show = this.showRepository.findById(showDto.getId())
//                .orElseThrow(() -> new ServiceException("There are no such show"));
//        this.showRepository.delete(show);
//    }
//
    public List<ShowDto> getAllShows() {
        List<Show> allShows = null;
        try {
            allShows = this.showRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allShows.stream()
                .map(this.showMapper::showToShowDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDto> findSuitableForUser(int id) {
        List<Show> allShows = null;
        try {
            allShows = this.showRepository.findSuitableForUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allShows.stream()
                .map(this.showMapper::showToShowDto)
                .collect(Collectors.toList());
    }
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

}
