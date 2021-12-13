package com.natali.cultickets.service;

import com.natali.cultickets.dto.ShowStateDto;
import com.natali.cultickets.model.ShowState;
import java.util.List;
import java.util.Optional;

public interface ShowStateService {
    Optional<ShowState> findShowState(int showState);

    List<ShowStateDto> findAll();
}
