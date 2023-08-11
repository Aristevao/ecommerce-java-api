package com.mentoring.ecommerce.application.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mentoring.ecommerce.application.port.in.user.FindUserUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final FindUserUseCase findUserUseCase;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return findUserUseCase.findByUsername(username) == null;
    }
}

