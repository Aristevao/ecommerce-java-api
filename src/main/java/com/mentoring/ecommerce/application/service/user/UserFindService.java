package com.mentoring.ecommerce.application.service.user;


import java.util.Optional;

import com.mentoring.common.exceptions.NotFoundException;
import com.mentoring.common.pagination.PageBuilder;
import com.mentoring.ecommerce.application.port.in.user.FindUserUseCase;
import com.mentoring.ecommerce.application.port.out.user.FindUserPort;
import com.mentoring.ecommerce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserFindService implements FindUserUseCase {

    private final FindUserPort port;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return port.findAll(new PageBuilder().setPage(pageable.getPageNumber()).build());
    }

    @Override
    public User findByUsername(String username) {
        return port.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = port.findById(id);
        user.orElseThrow(() -> new NotFoundException("User not found: " + id));
        return user.get();
    }
}
