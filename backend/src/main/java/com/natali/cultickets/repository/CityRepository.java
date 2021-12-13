package com.natali.cultickets.repository;

import com.natali.cultickets.model.City;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository {
    Optional<City> getById(int id);
}
