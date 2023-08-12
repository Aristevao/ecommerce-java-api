package com.mentoring.ecommerce.adapter.in.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String username;

    private String password;
}

