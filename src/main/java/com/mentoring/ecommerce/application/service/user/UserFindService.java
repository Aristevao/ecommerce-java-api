package com.mentoring.ecommerce.application.service.user;


import com.mentoring.ecommerce.application.port.in.user.FindUserUseCase;
import com.mentoring.ecommerce.application.port.out.user.FindUserPort;
import com.mentoring.ecommerce.domain.User;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserFindService implements FindUserUseCase {

    private final FindUserPort port;

    @Override
    public User findByUsername(String username) {
        return port.findByUsername(username);
    }
}
