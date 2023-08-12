package com.mentoring.ecommerce.application.service.user;


import com.mentoring.ecommerce.application.port.in.user.SaveUserUseCase;
import com.mentoring.ecommerce.application.port.out.user.SaveUserPort;
import com.mentoring.ecommerce.domain.User;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserSaveService implements SaveUserUseCase {

    private final SaveUserPort port;

    @Override
    public User save(User userRequest) {
        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(userRequest.getPassword());
        return port.save(newUser);
    }
}
