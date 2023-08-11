package com.mentoring.ecommerce.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class JwtResponse {
    private final String jwtToken;
}

