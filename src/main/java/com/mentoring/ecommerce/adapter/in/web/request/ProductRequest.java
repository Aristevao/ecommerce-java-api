package com.mentoring.ecommerce.adapter.in.web.request;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;

    private String supplier;

}
