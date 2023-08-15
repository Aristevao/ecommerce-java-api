package com.mentoring.ecommerce.application.port.out.user;

import com.mentoring.ecommerce.domain.User;

public interface SaveUserPort {

    User save(User user);
}
