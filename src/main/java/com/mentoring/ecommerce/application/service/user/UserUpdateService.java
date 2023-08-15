package com.mentoring.ecommerce.application.service.user;

import com.mentoring.ecommerce.application.port.in.user.UpdateUserUseCase;
import com.mentoring.ecommerce.domain.User;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserUpdateService implements UpdateUserUseCase {

    private final UserFindService userFindService;

    private final UserSaveService userSaveService;

    @Override
    public User update(User user, Integer id) {
        userFindService.findById(id);
        user.setId(id);
        return userSaveService.save(user);
    }
}
