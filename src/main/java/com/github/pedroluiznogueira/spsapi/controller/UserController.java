package com.github.pedroluiznogueira.spsapi.controller;

import com.github.pedroluiznogueira.spsapi.dto.TokenDto;
import com.github.pedroluiznogueira.spsapi.dto.UserDto;
import com.github.pedroluiznogueira.spsapi.model.User;
import com.github.pedroluiznogueira.spsapi.repository.UserRepository;
import com.github.pedroluiznogueira.spsapi.security.manager.UserService;
import com.github.pedroluiznogueira.spsapi.security.service.TokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
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
    public String insert(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.insert(user);
        return "inserted";
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> auth(@RequestBody UserDto user) {
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
        return ResponseEntity.ok(TokenDto.builder().type("Bearer").token(token).build());
    }
}
