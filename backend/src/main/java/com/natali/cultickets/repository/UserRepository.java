package com.natali.cultickets.repository;


import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.model.Role;
import com.natali.cultickets.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DataAccessConfig config;

    public AuthInfo findUserByLogin(String login) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select a.au_id, a.au_hash, a.au_salt, u.u_id from authorization as a " +
                        "join user as u on u.u_authorization_id = a.au_id where au_login = ?" );
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

    public Set<Role> getRoles(int userId) throws SQLException {
        Set<Role> roles = new HashSet<>();
        Connection connection = config.getConnection();
        PreparedStatement preparedStatementForRoles = connection.prepareStatement(
                "select r.r_id, r.r_name from mtm_user_role as mur " +
                        "join role as r on r.r_id = mur.mtm_role_id where mur.mtm_user_id = ? " );
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
}
