package com.mentoring.ecommerce.adapter.out.persistence.postgresql.user;

import java.util.Optional;

import com.mentoring.common.annotaion.PersistenceAdapter;
import com.mentoring.ecommerce.application.port.out.user.DeleteUserPort;
import com.mentoring.ecommerce.application.port.out.user.FindUserPort;
import com.mentoring.ecommerce.application.port.out.user.SaveUserPort;
import com.mentoring.ecommerce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class UserAdapter implements FindUserPort, SaveUserPort, DeleteUserPort {

    private final UserRepository repository;

    @Override
    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User save(final User user) {
        return repository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }
}