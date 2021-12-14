package com.natali.cultickets.repository;

import java.util.List;
import java.util.Optional;

import com.natali.cultickets.model.Genre;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class GenreRepository {
    public Optional<Genre> findById(int id) {return null;}

    public List<Genre> findAll() {return null;}
}
