package com.github.pedroluiznogueira.spsapi.repository;

import com.github.pedroluiznogueira.spsapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
