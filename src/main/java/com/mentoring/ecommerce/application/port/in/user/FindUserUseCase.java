package com.mentoring.ecommerce.application.port.in.user;

import com.mentoring.common.annotaion.UseCase;
import com.mentoring.ecommerce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
public interface FindUserUseCase {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    User findById(Integer id);
}
