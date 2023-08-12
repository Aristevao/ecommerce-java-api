package com.mentoring.ecommerce.adapter.in.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.mentoring.ecommerce.application.validation.UniqueUsername;
import org.springframework.hateoas.RepresentationModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserDTO extends RepresentationModel<UserDTO> {

    private Integer id;

    @Email
    @UniqueUsername
    private String username;

    @NotBlank
    private String password;
}

