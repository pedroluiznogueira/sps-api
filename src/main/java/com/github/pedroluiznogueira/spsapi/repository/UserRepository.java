package com.github.pedroluiznogueira.spsapi.repository;

import com.github.pedroluiznogueira.spsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String username);

    Optional<User> findById(Integer id);

    User findUserByName(String name);
}
