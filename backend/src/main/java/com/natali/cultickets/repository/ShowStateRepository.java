package com.natali.cultickets.repository;

import com.natali.cultickets.model.ShowState;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ShowStateRepository {
    Optional<ShowState> findById(int id);

    List<ShowState> findAll();
}
