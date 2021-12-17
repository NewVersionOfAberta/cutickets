package com.natali.cultickets.service;

import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.model.Show;

import java.sql.SQLException;
import java.util.List;

public interface ShowService {
//    void updateShow(ShowDto showDto);
//
//    Show saveShow(ShowDto showDto);
//
    List<ShowDto> findShows(int user_id, int theaterId, int typeId, int userId);

    List<ShowDto> findSuitableForUser(int id);

    List<ShowDto> findScheduledShowsByShow(int userId, int showId);

    ShowDto getShowInfo(int userId, int showId);

    List<ShowDto> getByTheatre(int userId, int id);

    List<ShowDto> findByGenre(int genreId);

    List<ShowDto> findAll();
//
//    void deleteShow(ShowDto showDto);
}
