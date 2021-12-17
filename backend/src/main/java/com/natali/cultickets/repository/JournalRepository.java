package com.natali.cultickets.repository;

import com.natali.cultickets.db.DataAccessConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class JournalRepository {

    @Autowired
    private DataAccessConfig config;

    public enum Operation {
        CREATE(1),
        READ(2),
        UPDATE(3),
        DELETE(4);

        private final int op_id;

        Operation(int op_id) {
            this.op_id = op_id;
        }
    }

    public void write(
            int userId,
            String table,
            String column,
            String value,
            Operation operation
    ) {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "call write_to_journal(?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, table);
            preparedStatement.setString(3, column);
            preparedStatement.setString(4, value);
            preparedStatement.setInt(5, operation.op_id);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



