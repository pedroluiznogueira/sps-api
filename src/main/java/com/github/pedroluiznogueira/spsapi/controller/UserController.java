package com.github.pedroluiznogueira.spsapi.controller;

import com.github.pedroluiznogueira.spsapi.dto.TokenDto;
import com.github.pedroluiznogueira.spsapi.dto.UserDto;
import com.github.pedroluiznogueira.spsapi.model.Repo;
import com.github.pedroluiznogueira.spsapi.model.User;
import com.github.pedroluiznogueira.spsapi.repository.UserRepository;
import com.github.pedroluiznogueira.spsapi.security.manager.UserService;
import com.github.pedroluiznogueira.spsapi.security.service.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/insert")
    public User insert(@RequestBody User user) throws InterruptedException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Thread.sleep(10000);
        return userRepository.insert(user);
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> auth(@RequestBody UserDto user) throws InterruptedException {
        // generate a token with user login info to deliver to token service
        UsernamePasswordAuthenticationToken loginCredentials
                = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        // encapsulates all login info token
        Authentication authentication = authenticationManager
                .authenticate(loginCredentials);

        // generate token and set's it in a data transfer object
        String token = tokenService.generateToken(authentication);
        Thread.sleep(10000);
        return ResponseEntity.ok(TokenDto.builder().type("Bearer").token(token).build());
    }

    @GetMapping("/find/user/{name}")
    public User findUserByName(@PathVariable ("name") String name) {
        return userRepository.findUserByName(name);
    }

    @GetMapping("/find/all/repos/{name}")
    public List<Repo> findAllRepos(@PathVariable ("name") String name) {
        User user = userRepository.findUserByName(name);
        return user.getRepos();
    }

    @PostMapping("/insert/repo")
    public User insertRepo(@RequestBody Repo repo) {
        User user = userRepository.findUserByName(repo.getUserName());
        List<Repo> listToInsertIn = user.getRepos();
        listToInsertIn.add(repo);
        user.setRepos(listToInsertIn);
        userRepository.save(user);
        return user;
    }

    @PostMapping("/delete/repo")
    public User deleteRepo(@RequestBody Repo repo) {
        User user = userRepository.findUserByName(repo.getUserName());
        List<Repo> listToDeleteFrom = user.getRepos();
        listToDeleteFrom.remove(repo);
        user.setRepos(listToDeleteFrom);
        userRepository.save(user);
        return user;
    }
}
