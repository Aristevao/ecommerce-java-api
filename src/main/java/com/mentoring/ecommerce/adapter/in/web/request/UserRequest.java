package com.mentoring.ecommerce.adapter.in.web.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String username;
    private String password;
}

