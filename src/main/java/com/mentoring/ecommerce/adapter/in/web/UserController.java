package com.mentoring.ecommerce.adapter.in.web;

import javax.validation.Valid;

import com.mentoring.ecommerce.adapter.in.web.mapper.UserMapper;
import com.mentoring.ecommerce.adapter.in.web.request.UserRequest;
import com.mentoring.ecommerce.adapter.in.web.response.UserResponse;
import com.mentoring.ecommerce.application.port.in.user.SaveUserUseCase;
import com.mentoring.ecommerce.domain.User;
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

    private final UserMapper productMapper;

    @PostMapping
    public UserResponse save(@RequestBody @Valid UserRequest request) {
        final User user = saveUserUseCase.save(productMapper.toDomain(request));
        saveUserUseCase.save(user);
        return productMapper.toResponse(user);
    }
}






