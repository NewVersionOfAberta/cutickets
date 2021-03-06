package com.natali.cultickets.repository;


import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DataAccessConfig config;

    public List<UserDto> getAllUsers() throws SQLException {
        Connection connection = config.getConnection();
        List<UserDto> userDtos = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select u.u_id, au.au_login, asd.as_name, pd.pd_name, pd.pd_surname, pd.pd_email from authorization as au \n" +
                        "join `user` as u on u.u_authorization_id = au.au_id\n" +
                        "join personal_data as pd on u.u_personal_data_id = pd.pd_id\n" +
                        "join account_status as asd on u.u_account_status_id = asd.as_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            UserDto userDto = new UserDto();
            String active = resultSet.getString("as_name");
            userDto.setId(resultSet.getInt("u_id"));
            userDto.setActive("active".equals(active));
            userDto.setEmail(resultSet.getString("pd_email"));
            userDto.setUserName(resultSet.getString("au_login"));
            userDto.setName(resultSet.getString("pd_name"));
            userDto.setSurname(resultSet.getString("pd_surname"));
            userDtos.add(userDto);
        }
        resultSet.close();
        return userDtos;
    }

    public UserDto getUserInfo(int userId) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select u.u_id, au.au_login, pd.pd_name, pd.pd_surname, pd.pd_patronymic, " +
                        "pd.pd_birthdate, pd.pd_email, c.c_name from authorization as au \n" +
                        "join `user` as u on u.u_authorization_id = au.au_id\n" +
                        "join personal_data as pd on u.u_personal_data_id = pd.pd_id\n" +
                        "join city as c on c.c_id = pd.pd_city_id\n" +
                        "where u.u_id = ?;");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDto userDto = new UserDto();
        resultSet.next();
        userDto.setId(resultSet.getInt("u_id"));
        userDto.setEmail(resultSet.getString("pd_email"));
        userDto.setUserName(resultSet.getString("au_login"));
        userDto.setName(resultSet.getString("pd_name"));
        userDto.setSurname(resultSet.getString("pd_surname"));
        userDto.setPatronymic(resultSet.getString("pd_patronymic"));
        userDto.setBirthDate(resultSet.getDate("pd_birthdate"));
        userDto.setCity(resultSet.getString("c_name"));
        preparedStatement.close();
        resultSet.close();
        return userDto;
    }

    public AuthInfo findUserByLogin(String login) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select a.au_id, a.au_hash, a.au_salt, u.u_id from authorization as a " +
                        "join user as u on u.u_authorization_id = a.au_id where au_login = ?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        AuthInfo authInfo = new AuthInfo();

        resultSet.next();
        authInfo.setId(resultSet.getInt("au_id"));
        authInfo.setPasswordHash(resultSet.getString("au_hash"));
        authInfo.setSalt(resultSet.getString("au_salt"));
        authInfo.setUserId(resultSet.getInt("u_id"));
        authInfo.setLogin(login);
        resultSet.close();
        return authInfo;
    }

    public int findCityId(int userId) {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "select c.c_id from user as u " +
                            "join personal_data as pd on pd.pd_id = u.u_personal_data_id " +
                            "join city as c on c.c_id = pd.pd_city_id " +
                            "where u.u_id = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("c_id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Set<Role> getRoles(int userId) throws SQLException {
        Set<Role> roles = new HashSet<>();
        Connection connection = config.getConnection();
        PreparedStatement preparedStatementForRoles = connection.prepareStatement(
                "select r.r_id, r.r_name from mtm_user_role as mur " +
                        "join role as r on r.r_id = mur.mtm_role_id where mur.mtm_user_id = ? ");
        preparedStatementForRoles.setInt(1, userId);
        ResultSet resultSet = preparedStatementForRoles.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("r_name");
            int id = resultSet.getInt("r_id");
            roles.add(new Role(id, name));
        }
        resultSet.close();
        return roles;
    }

    public void disableUser(int userId) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update `user` as u " +
                "set u.u_account_status_id = (select astat.as_id from account_status as astat where astat.as_name = \"inactive\") " +
                "where u.u_id = ?");
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();
    }

    public void activateUser(int userId) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update `user` as u " +
                "set u.u_account_status_id = (select astat.as_id from account_status as astat where astat.as_name = \"active\") " +
                "where u.u_id = ?");
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();
    }

    public List<Genre> getPreferableGenres(int userId) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        Connection connection = config.getConnection();
        PreparedStatement preparedStatementForRoles = connection.prepareStatement(
                "call find_preferable_user_genres(?);");
        preparedStatementForRoles.setInt(1, userId);
        ResultSet resultSet = preparedStatementForRoles.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("g_name");
            int id = resultSet.getInt("g_id");
            genres.add(new Genre(id, name));
        }
        resultSet.close();
        return genres;
    }

    public List<Expenses> getUserExpenses(int userId) throws SQLException {
        List<Expenses> expenses = new ArrayList<>();
        Connection connection = config.getConnection();
        PreparedStatement preparedStatementForRoles = connection.prepareStatement(
                "call get_user_bought_tickets_statistics(?);");
        preparedStatementForRoles.setInt(1, userId);
        ResultSet resultSet = preparedStatementForRoles.executeQuery();
        while (resultSet.next()) {
            String month = resultSet.getString("month");
            int exps = resultSet.getInt("total_exps");
            expenses.add(new Expenses(month, exps));
        }
        resultSet.close();
        return expenses;
    }
}
