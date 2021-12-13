package com.natali.cultickets.repository;

import com.natali.cultickets.model.Theater;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository {
    Optional<Theater> findById(int id);

    List<Theater> findAll();
}
