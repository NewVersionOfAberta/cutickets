package com.natali.cultickets.service;

import com.natali.cultickets.dto.ExpensesDto;
import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.dto.UserPostDto;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.model.Expenses;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    void checkUserExists(String email);
//
//    Optional<UserDto> findUserDtoByEmail(String email);
//
//    Optional<User> findUserByEmail(String email);
//
//    User saveUser(UserPostDto userPostDto);
    List<UserDto> getAllUsers();
    void disableUser(UserDto user);
    void activateUser(UserDto userDto);
    void updateUserStatus(String status);
    void updateUserAccount(UserDto user);
    UserDto getUserInfo(int userId);
    List<GenreDto> getPreferableGenres(int userId);
    List<ExpensesDto> getUserExpenses(int userId);
    AuthInfo findByLogin(String login);
}
