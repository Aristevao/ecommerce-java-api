package com.mentoring.ecommerce.adapter.out.persistence.postgresql.user;

import com.mentoring.common.annotaion.PersistenceAdapter;
import com.mentoring.ecommerce.application.port.out.user.FindUserPort;
import com.mentoring.ecommerce.application.port.out.user.SaveUserPort;
import com.mentoring.ecommerce.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserAdapter implements FindUserPort, SaveUserPort {

    private final UserRepository repository;

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User save(final User user) {
        return repository.save(user);
    }
}