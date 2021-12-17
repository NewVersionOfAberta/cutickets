package com.natali.cultickets.service.impl;

import com.natali.cultickets.dto.ExpensesDto;
import com.natali.cultickets.dto.GenreDto;
import com.natali.cultickets.dto.UserDto;
import com.natali.cultickets.mapstruct.ExpensesMapper;
import com.natali.cultickets.mapstruct.GenreMapper;
import com.natali.cultickets.mapstruct.UserMapper;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.repository.JournalRepository;
import com.natali.cultickets.repository.UserRepository;
import com.natali.cultickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.natali.cultickets.repository.JournalRepository.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final GenreMapper genreMapper;
    private final ExpensesMapper expensesMapper;
    private final JournalRepository journalRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserMapper mapstructMapper,
                           GenreMapper genreMapper,
                           ExpensesMapper expensesMapper,
                           JournalRepository journalRepository) {
        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = mapstructMapper;
        this.genreMapper = genreMapper;
        this.expensesMapper = expensesMapper;
        this.journalRepository = journalRepository;
    }

    @Override
    public List<UserDto> getAllUsers(int userId) {
        try {
            journalRepository.write(userId, "user", null, null, Operation.READ);
            journalRepository.write(userId, "authorization", "au_id", null, Operation.READ);
            journalRepository.write(userId, "personal_data", "pd_id", null, Operation.READ);
            journalRepository.write(userId, "account_status", "as_id", null, Operation.READ);

            return userRepository.getAllUsers();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    @Override
    public void disableUser(int userId, UserDto user) {
        try {
            journalRepository.write(userId, "user", "u_account_status_id",
                    String.valueOf(3), Operation.UPDATE);
            userRepository.disableUser(user.getId());
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    @Override
    public void activateUser(int userId, UserDto user) {
        try {
            journalRepository.write(userId, "user", "u_account_status_id",
                    String.valueOf(1), Operation.UPDATE);
            userRepository.activateUser(user.getId());
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateUserStatus(int userId, String status) {

    }

    @Override
    public void updateUserAccount(int userId, UserDto user) {

    }

    @Override
    public UserDto getUserInfo(int userId) {
        try {
            journalRepository.write(userId, "user", "u_id",
                    String.valueOf(userId), Operation.READ);
            return userRepository.getUserInfo(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GenreDto> getPreferableGenres(int userId) {
        try {
            journalRepository.write(userId, "user", "u_id",
                    String.valueOf(userId), Operation.READ);
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

    @Override
    public AuthInfo findByLogin(String login) {
        try {
            return userRepository.findUserByLogin(login);
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
