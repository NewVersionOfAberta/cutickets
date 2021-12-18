package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.mapstruct.ShowMapper;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.model.Show;
import com.natali.cultickets.repository.JournalRepository;
import com.natali.cultickets.repository.ShowRepository;
import com.natali.cultickets.repository.UserRepository;
import com.natali.cultickets.service.GenreService;
import com.natali.cultickets.service.ShowService;
import com.natali.cultickets.service.TheatreService;
import com.natali.cultickets.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final JournalRepository journalRepository;
    private final ShowMapper showMapper;
    private final TheatreService theaterService;
    private final GenreService genreService;
    private final UserRepository userRepository;

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository, ShowMapper showMapper, TheatreService theaterService,
                           GenreService genreService, JournalRepository journalRepository, UserRepository userRepository) {
        this.showRepository = showRepository;
        this.showMapper = showMapper;
        this.theaterService = theaterService;
        this.genreService = genreService;
        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
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
    public List<ShowDto> findShows(int user_id, int theaterId, int genreId, int userId) {
        if (theaterId == 0 && genreId == 0 && userId == 0) {
            journalRepository.write(user_id, "show", null, null, JournalRepository.Operation.READ);
            return findAll();
        }
        else if(theaterId == 0 && genreId == 0) {
            journalRepository.write(user_id, "show", "sh_theatre_id",
                    null, JournalRepository.Operation.READ);
            journalRepository.write(user_id, "theatre", "t_address_id",
                    null, JournalRepository.Operation.READ);
            journalRepository.write(user_id, "address", "add_city_id",
                    null, JournalRepository.Operation.READ);
            journalRepository.write(user_id, "city", "c_name",
                    String.valueOf(userRepository.findCityId(userId)), JournalRepository.Operation.READ);
            return findSuitableForUser(userId);
        }
        else if (genreId == 0 && userId == 0) {
            journalRepository.write(user_id, "show", "sh_theatre_id",
                    null, JournalRepository.Operation.READ);
            journalRepository.write(user_id, "theatre", "t_id",
                    String.valueOf(theaterId), JournalRepository.Operation.READ);
            return getByTheatre(user_id, theaterId);
        }
        else if (theaterId == 0 && userId == 0) {
            journalRepository.write(user_id, "mtm_show_genre", "mtm_genre_id",
                    String.valueOf(genreId), JournalRepository.Operation.READ);
            journalRepository.write(user_id, "theatre", "t_id",
                    String.valueOf(theaterId), JournalRepository.Operation.READ);
            return findByGenre(genreId);
        }
        return null;
    }

//    @Override
//    public void deleteShow(ShowDto showDto) {
//        Show show = this.showRepository.findById(showDto.getId())
//                .orElseThrow(() -> new ServiceException("There are no such show"));
//        this.showRepository.delete(show);
//    }

    public List<ShowDto> findAll() {
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

    @Override
    public List<ShowDto> findScheduledShowsByShow(int userId, int showId) {
        List<Show> allShows = null;
        try {
            journalRepository.write(userId, "scheduled_show", "ss_show_id",
                    String.valueOf(showId), JournalRepository.Operation.READ);
            allShows = this.showRepository.findScheduledShowsByShow(showId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allShows.stream()
                .map(this.showMapper::showToShowDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShowDto getShowInfo(int userId, int showId) {
        Show show = null;
        try {
            journalRepository.write(userId, "show", "sh_id",
                    String.valueOf(showId), JournalRepository.Operation.READ);
            show = this.showRepository.getShowInfo(showId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showMapper.showToShowDto(show);
    }

    @Override
    public List<ShowDto> getByTheatre(int userId, int id) {
        List<Show> allShows = null;
        try {
            allShows = this.showRepository.findByTheatre(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allShows.stream()
                .map(this.showMapper::showToShowDto)
                .collect(Collectors.toList());
    }

    public List<ShowDto> findByGenre(int genreId) {
        Genre genre = this.genreService.findById(genreId);
        List<Show> showsByType = null;
        try {
            showsByType = this.showRepository.findByGenre(genreId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showsByType.stream()
                .map(this.showMapper::showToShowDto)
                .collect(Collectors.toList());
    }
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
