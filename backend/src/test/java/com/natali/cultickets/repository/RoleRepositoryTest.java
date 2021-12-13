package com.natali.cultickets.repository;

import com.natali.cultickets.model.Role;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoleRepositoryTest {
    private final Flyway flyway;
    private final RoleRepository roleRepository;

    @Autowired
    RoleRepositoryTest(Flyway flyway, RoleRepository roleRepository) {
        this.flyway = flyway;
        this.roleRepository = roleRepository;
    }

    @AfterEach
    void restoreDatabase() {
        this.flyway.clean();
        this.flyway.migrate();
    }

    @Test
    void findByRole_adminAndUserRoleNames_exist() {
        String adminRoleName = "ROLE_ADMIN", userRoleName = "ROLE_USER";

        Role adminRole = this.roleRepository.findByRole(adminRoleName);
        Role userRole = this.roleRepository.findByRole(userRoleName);

        assertNotNull(adminRole);
        assertNotNull(userRole);
    }

    @Test
    void findByRole_testRoleName_null() {
        String testRoleName = "test";

        Role testRole = this.roleRepository.findByRole(testRoleName);

        assertNull(testRole);
    }

}