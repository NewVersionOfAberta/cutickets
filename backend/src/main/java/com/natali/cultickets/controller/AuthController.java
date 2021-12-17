package com.natali.cultickets.controller;

import com.natali.cultickets.dto.UserLoginDto;
import com.natali.cultickets.dto.UserPostDto;
import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.model.Theatre;
import com.natali.cultickets.repository.BaseRepository;
import com.natali.cultickets.repository.JournalRepository;
import com.natali.cultickets.repository.TheatreRepository;
import com.natali.cultickets.security.jwt.JwtUtils;
//import com.natali.cultickets.service.UserService;
//import com.natali.cultickets.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.natali.cultickets.service.detailsService.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.natali.cultickets.repository.JournalRepository.*;

@Slf4j
@RestController
public class AuthController {

//    private final UserService userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtTokenUtil;

    private final AuthenticationManager authenticationManager;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private final JournalRepository journalRepository;
    @Autowired
    public AuthController(
//            UserServiceImpl userService,
            JwtUtils jwtTokenUtil, AuthenticationManager authenticationManager,
            JournalRepository journalRepository) {
//        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.journalRepository = journalRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginDto user) {
        Map<String, Object> response = new HashMap<>();
        String login = user.getLogin();
        String password = user.getPassword();
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        AuthInfo authInfo = userDetailsService.getUser();
        int id = authInfo.getUserId();
        journalRepository.write(id, "authorization", null, null, Operation.READ);

        String jwtToken = this.jwtTokenUtil.generateToken(authInfo, userDetailsService.getAuthorities());
        response.put("token", jwtToken);
        authInfo.setPasswordHash("");
        response.put("user", authInfo);
        response.put("roles", userDetailsService.getAuthorities());
        ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity.ok(response);
        log.info("Response = {}", responseEntity);
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserPostDto user) {
        Map<String, String> response = new HashMap<>();
        try {
//            this.userService.checkUserExists(user.getEmail());
//            this.userService.saveUser(user);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Successfully registered");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/natashka")
    public ResponseEntity<Map<String, String>> nat() {
//        TheatreRepository theatreRepository = new TheatreRepository();
        Map<String, String> response = new HashMap<>();
        Optional<Theatre> theatreOpt;
        Theatre theatre;
        try {
            theatreOpt = theatreRepository.findById(1);
            if (theatreOpt.isPresent()) {
                theatre = theatreOpt.get();
                response.put("theatre", "id=" + theatre.getId() +
                        ", name=" + theatre.getName() +
                        ", description=" + theatre.getDescription());
            }
            else {
                response.put("null", "There is no theatre with id ");
            }
        } catch (SQLException throwables) {
            response.put("error", throwables.getMessage());
            throwables.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }
}
