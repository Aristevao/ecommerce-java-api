package com.mentoring.ecommerce.adapter.in.web.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.mentoring.ecommerce.application.validation.UniqueUsername;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    @Email
    @UniqueUsername
    private String username;

    @NotBlank
    private String password;
}

