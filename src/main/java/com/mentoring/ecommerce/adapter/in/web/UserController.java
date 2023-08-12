package com.mentoring.ecommerce.adapter.in.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import com.mentoring.common.pagination.PageBuilder;
import com.mentoring.ecommerce.adapter.in.web.dto.UserDTO;
import com.mentoring.ecommerce.adapter.in.web.mapper.UserMapper;
import com.mentoring.ecommerce.application.port.in.user.DeleteUserUserCase;
import com.mentoring.ecommerce.application.port.in.user.FindUserUseCase;
import com.mentoring.ecommerce.application.port.in.user.SaveUserUseCase;
import com.mentoring.ecommerce.application.port.in.user.UpdateUserUserCase;
import com.mentoring.ecommerce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final SaveUserUseCase saveUserUseCase;

    private final FindUserUseCase findUseCase;

    private final UpdateUserUserCase updateUseCase;

    private final DeleteUserUserCase deleteUseCase;

    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public UserDTO save(@Valid @RequestBody UserDTO request) {
        User user = saveUserUseCase.save(userMapper.toEntity(request));
        return userMapper.toDto(user)
                .add(linkTo(methodOn(UserController.class).findUserById(user.getId())).withSelfRel())
                .add(linkTo(methodOn(UserController.class).findAllUsers(new PageBuilder().build())).withRel("users"));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAllUsers(Pageable pageable) {
        Page<User> users = findUseCase.findAll(pageable);
        Page<UserDTO> response = userMapper.toDto(users);
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("{userId}")
    public UserDTO findUserById(@PathVariable(name = "userId") Integer id) {
        return userMapper.toDto(findUseCase.findById(id))
                .add(linkTo(UserController.class).slash(id).withSelfRel())
                .add(linkTo(methodOn(UserController.class).findUserById(id)).withRel("update"))
                .add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"))
                .add(linkTo(methodOn(UserController.class).findAllUsers(new PageBuilder().build())).withRel("users"));
    }

    @PutMapping("{userId}")
    public UserDTO updateUser(
            @RequestBody UserDTO request,
            @PathVariable(name = "userId") Integer id) {
        User user = updateUseCase.update(userMapper.toEntity(request), id);
        return userMapper.toDto(user)
                .add(linkTo(methodOn(UserController.class).findUserById(id)).withSelfRel())
                .add(linkTo(methodOn(UserController.class).findAllUsers(new PageBuilder().build())).withRel("users"));
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") Integer id) {
        deleteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}






