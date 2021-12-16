package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.ExpensesDto;
import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.mapstruct.ExpensesMapper;
import com.natali.cultickets.mapstruct.GenreMapper;
import com.natali.cultickets.mapstruct.UserMapper;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.repository.UserRepository;
import com.natali.cultickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final GenreMapper genreMapper;
    private final ExpensesMapper expensesMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserMapper mapstructMapper,
                           GenreMapper genreMapper,
                           ExpensesMapper expensesMapper) {
        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = mapstructMapper;
        this.genreMapper = genreMapper;
        this.expensesMapper = expensesMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        try {
            return userRepository.getAllUsers();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    @Override
    public void disableUser(UserDto user) {
        try {
            userRepository.disableUser(user.getId());
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    @Override
    public void activateUser(UserDto user) {
        try {
            userRepository.activateUser(user.getId());
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateUserStatus(String status) {

    }

    @Override
    public void updateUserAccount(UserDto user) {

    }

    @Override
    public UserDto getUserInfo(int userId) {
        try {
            return userRepository.getUserInfo(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GenreDto> getPreferableGenres(int userId) {
        try {
            return userRepository.getPreferableGenres(userId).stream()
                    .map(this.genreMapper::genreToGenreDto)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExpensesDto> getUserExpenses(int userId) {
        try {
            return userRepository.getUserExpenses(userId).stream()
                    .map(this.expensesMapper::expsToexpsDto)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


//    public void checkUserExists(String email) {
//        this.userRepository.findByEmail(email)
//                .ifPresent((v) -> {
//                    throw new ServiceException("There is already a user exists with the provided email");
//                });
//    }

//    public Optional<UserDto> findUserDtoByEmail(String email) {
//        Optional<User> user = this.userRepository.findByEmail(email);
//        return user.map(this.mapstructMapper::userToUserDto);
//    }

//    @Override
//    public Optional<User> findUserByEmail(String email) {
//        return this.userRepository.findByEmail(email);
//    }

//    public User saveUser(UserPostDto userPostDto) {
//        User user = this.mapstructMapper.userPostDtoToUser(userPostDto);
//        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(true);
//        Role userRole = this.roleRepository.findByRole("ROLE_USER");
//        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
//        return this.userRepository.save(user);
//    }


}
