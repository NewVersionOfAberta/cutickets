package com.natali.cultickets.repository;

import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.model.Journal;
import com.natali.cultickets.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<Journal> getJournalInfo() {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = null;
        List<Journal> journals = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(
                    "select jo.jo_id, jo.jo_time, au.au_login, oi.oi_table, " +
                            "oi.oi_column, oi.oi_value, op.op_name from journal as jo " +
                            "join operation_info as oi on oi.oi_id = jo.jo_operation_info_id " +
                            "join operation as op on op.op_id = oi.oi_operation_id " +
                            "join user as u on u.u_id = jo.jo_user_id " +
                            "join authorization as au on au.au_id = u.u_authorization_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Journal journal = new Journal();
                journal.setId(resultSet.getInt("jo.jo_id"));
                LocalDateTime datetime = resultSet.getTimestamp("jo_time").toLocalDateTime();
                String[] dts = datetime.toString().split("T");
                journal.setTime(dts[0] + " " + dts[1]);
                journal.setLogin(resultSet.getString("au.au_login"));
                journal.setTable(resultSet.getString("oi.oi_table"));
                journal.setColumn(resultSet.getString("oi.oi_column"));
                journal.setValue(resultSet.getString("oi.oi_value"));
                journal.setOperation(resultSet.getString("op.op_name"));
                journals.add(journal);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journals;
    }
}



