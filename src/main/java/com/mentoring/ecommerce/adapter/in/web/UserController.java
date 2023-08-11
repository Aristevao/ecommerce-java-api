package com.mentoring.ecommerce.adapter.in.web;

import javax.validation.Valid;

import com.mentoring.ecommerce.adapter.in.web.request.UserRequest;
import com.mentoring.ecommerce.application.port.in.user.FindUserUseCase;
import com.mentoring.ecommerce.application.port.in.user.SaveUserUseCase;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRequest userRequest) {

        saveUserUseCase.save(userRequest);

        return ResponseEntity.ok("User registered successfully.");
    }
}






