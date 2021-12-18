package com.natali.cultickets.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class GenreRepository {

    @Autowired
    private DataAccessConfig config;

    public List<Genre> findAll() throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select g_id, g_name from genre;");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Genre> genres = new ArrayList<>();

        while (resultSet.next()) {
            Genre genre = new Genre();
            genre.setId(resultSet.getInt("g_id"));
            genre.setName(resultSet.getString("g_name"));
            genres.add(genre);
        }
        resultSet.close();
        return genres;
    }

    public Genre findById(int genreId) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select g_name from genre where g.g_id = ?");
        preparedStatement.setInt(1, genreId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Genre genre = new Genre();
        genre.setName(resultSet.getString("g_name"));
        resultSet.close();
        return genre;
    }
}
