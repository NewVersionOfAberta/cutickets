package com.natali.cultickets.repository;


import com.natali.cultickets.model.AuthInfo;
import com.natali.cultickets.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    Optional<AuthInfo> findUserByLogin(String login);
}
