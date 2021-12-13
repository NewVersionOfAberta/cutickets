package com.natali.cultickets.controller;

import com.natali.cultickets.dto.UserLoginDto;
import com.natali.cultickets.dto.UserPostDto;
import com.natali.cultickets.repository.BaseRepository;
import com.natali.cultickets.security.jwt.JwtUtils;
//import com.natali.cultickets.service.UserService;
//import com.natali.cultickets.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

//    private final UserService userService;

    private final JwtUtils jwtTokenUtil;

    private final AuthenticationManager authenticationManager;
    private final BaseRepository repository;
    @Autowired
    public AuthController(
//            UserServiceImpl userService,
            JwtUtils jwtTokenUtil, AuthenticationManager authenticationManager, BaseRepository repository) {
//        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginDto user) {
        Map<String, Object> response = new HashMap<>();
        String email = user.getEmail();
        String password = user.getPassword();
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//        this.userService.findUserDtoByEmail(email).ifPresent((u) -> {
//            String jwtToken = this.jwtTokenUtil.generateToken(u);
//            response.put("token", jwtToken);
//            response.put("user", u);
//        });
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
    public ResponseEntity<String> nat() {
        try {
            repository.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ResponseEntity<>("Hello, Katya", HttpStatus.CREATED);
    }
}
