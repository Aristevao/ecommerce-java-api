package com.mentoring.ecommerce.application.port.in.user;

import com.mentoring.common.annotaion.UseCase;

@UseCase
public interface DeleteUserUseCase {

    void delete(final Integer id);
}
