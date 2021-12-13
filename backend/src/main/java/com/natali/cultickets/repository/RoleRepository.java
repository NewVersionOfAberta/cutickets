package com.natali.cultickets.repository;

import com.natali.cultickets.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
    Role findByRole(String role);

}
