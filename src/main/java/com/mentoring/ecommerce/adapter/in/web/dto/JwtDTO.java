package com.mentoring.ecommerce.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class JwtDTO {
    private final String jwtToken;
}

