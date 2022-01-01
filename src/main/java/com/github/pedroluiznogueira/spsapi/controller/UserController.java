package com.github.pedroluiznogueira.spsapi.controller;

import com.github.pedroluiznogueira.spsapi.model.User;
import com.github.pedroluiznogueira.spsapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // testing mongo
    @GetMapping("/insert")
    public String insert() {
        User user = new User("pedro", "pedro@pedro.com", "pedro");
        userRepository.insert(user);
        return "inserted";
    }
}
