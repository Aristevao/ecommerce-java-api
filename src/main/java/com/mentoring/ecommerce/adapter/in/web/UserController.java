package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.request.UserRequest;
import com.mentoring.ecommerce.application.port.in.user.FindUserUseCase;
import com.mentoring.ecommerce.application.port.in.user.SaveUserUseCase;
import com.mentoring.ecommerce.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        if (findUserUseCase.findByUsername(userRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        saveUserUseCase.save(newUser);

        return ResponseEntity.ok("User registered successfully.");
    }
}






