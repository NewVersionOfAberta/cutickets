package com.natali.cultickets.repository;

import com.natali.cultickets.model.User;
import java.util.Optional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {
    private final Flyway flyway;
    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(Flyway flyway, UserRepository userRepository) {
        this.flyway = flyway;
        this.userRepository = userRepository;
    }

    @AfterEach
    void restoreDatabase() {
        this.flyway.clean();
        this.flyway.migrate();
    }

    @Test
    void findByEmail_rightEmail_userFound() {
        String testEmail = "m@mail.ru";

        Optional<User> user = this.userRepository.findByEmail(testEmail);

        assertTrue(user.isPresent());
    }

    @Test
    void findByEmail_wrongEmail_userNotFound() {
        String testEmail = "m@mail777.ru";

        Optional<User> user = this.userRepository.findByEmail(testEmail);

        assertTrue(user.isEmpty());
    }
}