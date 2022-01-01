package com.github.pedroluiznogueira.spsapi.repository;

import com.github.pedroluiznogueira.spsapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByEmail(String username);

    Optional<User> findById(String id);
}
