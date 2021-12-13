package com.natali.cultickets.repository;

import com.natali.cultickets.db.DataAccessConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class BaseRepository {

    @Autowired
    private DataAccessConfig config;

    public void execute() throws SQLException {
        Connection connection = config.getConnection();
        CallableStatement callableStatement = connection.prepareCall("select * from theatre");
        ResultSet resultSet = callableStatement.executeQuery();
        resultSet.next();
        int t_id = resultSet.getInt("t_id");
        resultSet.next();
        resultSet.close();
    }
}
