package com.natali.cultickets.repository;

import com.natali.cultickets.model.ShowType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTypeRepository {
    Optional<ShowType> findById(int id);

    List<ShowType> findAll();
}
