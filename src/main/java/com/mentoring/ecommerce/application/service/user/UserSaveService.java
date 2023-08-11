package com.mentoring.ecommerce.application.service.user;


import com.mentoring.ecommerce.adapter.in.web.request.UserRequest;
import com.mentoring.ecommerce.application.port.in.user.FindUserUseCase;
import com.mentoring.ecommerce.application.port.in.user.SaveUserUseCase;
import com.mentoring.ecommerce.application.port.out.user.SaveUserPort;
import com.mentoring.ecommerce.domain.User;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserSaveService implements SaveUserUseCase {

    private final SaveUserPort port;

    private final FindUserUseCase findUserUseCase;

    @Override
    public User save(UserRequest userRequest) {
        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(userRequest.getPassword());
        return port.save(newUser);
    }
}
