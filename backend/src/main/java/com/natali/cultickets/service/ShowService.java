package com.natali.cultickets.service;

import com.natali.cultickets.dto.ShowDto;
import com.natali.cultickets.model.Show;
import java.util.List;

public interface ShowService {
    void updateShow(ShowDto showDto);

    Show saveShow(ShowDto showDto);

    List<ShowDto> findShows(int theaterId, int typeId);

    void deleteShow(ShowDto showDto);
}
