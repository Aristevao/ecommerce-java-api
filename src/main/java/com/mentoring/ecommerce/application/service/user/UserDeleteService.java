package com.mentoring.ecommerce.application.service.user;

import com.mentoring.ecommerce.application.port.in.user.DeleteUserUserCase;
import com.mentoring.ecommerce.application.port.out.user.DeleteUserPort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserDeleteService implements DeleteUserUserCase {

    private final UserFindService userFindService;

    private final DeleteUserPort port;

    @Override
    public void delete(final Integer id) {
        userFindService.findById(id);
        port.delete(id);
    }
}
