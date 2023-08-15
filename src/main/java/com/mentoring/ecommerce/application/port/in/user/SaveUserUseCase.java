package com.mentoring.ecommerce.application.port.in.user;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.User;

@UseCase
public interface SaveUserUseCase {

    User save(User user);
}
