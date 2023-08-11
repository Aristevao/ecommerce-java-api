package com.mentoring.ecommerce.application.port.in.user;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.User;

@UseCase
public interface FindUserUseCase {

    User findByUsername(String username);
}
