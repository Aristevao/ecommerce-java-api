package com.mentoring.ecommerce.application.service.user;

import com.mentoring.ecommerce.application.port.in.user.DeleteUserUseCase;
import com.mentoring.ecommerce.application.port.out.user.DeleteUserPort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserDeleteServiceUse implements DeleteUserUseCase {

    private final UserFindService userFindService;

    private final DeleteUserPort port;

    @Override
    public void delete(Integer id) {
        userFindService.findById(id);
        port.delete(id);
    }
}
