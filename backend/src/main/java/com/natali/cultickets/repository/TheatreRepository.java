package com.natali.cultickets.repository;

import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.model.Theatre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TheatreRepository {

    @Autowired
    private DataAccessConfig config;

    public Optional<Theatre> findById(int id) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select t_id, t_name, t_description, t_address_id from theatre" +
                        " where t_id = ?" );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Theatre theatre = new Theatre();

        resultSet.next();
        theatre.setId(resultSet.getInt("t_id"));
        theatre.setName(resultSet.getString("t_name"));
        theatre.setDescription(resultSet.getString("t_description"));
        theatre.setAddress_id(resultSet.getInt("t_address_id"));
        resultSet.close();
        return Optional.of(theatre);
    }

//    List<Theatre> findAll();
}
