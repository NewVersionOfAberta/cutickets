package com.natali.cultickets.db;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataAccessConfig {
    public static final String DB_URL = "jdbc:mysql://192.168.43.19:3306/Cultickets";
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    @Getter
    private final Connection connection;

    public DataAccessConfig() throws ClassNotFoundException, SQLException {
        Class.forName(DB_Driver);
        connection = DriverManager.getConnection(DB_URL, "student", "07042001_Kat");
    }
}
