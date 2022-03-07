package com.mentoring.ecommerce.adapter.in.web.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {

    @NotNull
    @NotBlank
    private String name;

    private String description;

    @Min(value = 0)
    @Max(value = 9999999)
    private BigDecimal price;

    @NotNull
    @NotBlank
    private String supplier;
}
