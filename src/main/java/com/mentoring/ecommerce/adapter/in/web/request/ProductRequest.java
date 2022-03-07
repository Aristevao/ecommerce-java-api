package com.mentoring.ecommerce.adapter.in.web.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    private String description;

    @Min(value = 0)
    @Max(value = 9999999)
    private BigDecimal price;

    @NotBlank
    private String supplier;
}
