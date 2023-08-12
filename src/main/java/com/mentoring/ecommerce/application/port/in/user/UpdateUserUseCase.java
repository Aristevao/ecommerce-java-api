package com.mentoring.ecommerce.application.port.in.user;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.User;

@UseCase
public interface UpdateUserUseCase {

    User update(final User user, final Integer id);
}
