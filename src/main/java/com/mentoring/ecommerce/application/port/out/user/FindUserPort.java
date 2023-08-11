package com.mentoring.ecommerce.application.port.out.user;

import com.mentoring.ecommerce.domain.User;

public interface FindUserPort {

    User findByUsername(String pageable);
}
