package com.mentoring.ecommerce.application.port.out.user;

import java.util.Optional;

import com.mentoring.ecommerce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindUserPort {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String pageable);

    Optional<User> findById(final Integer id);
}
