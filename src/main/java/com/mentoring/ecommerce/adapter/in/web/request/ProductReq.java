package com.mentoring.ecommerce.adapter.in.web.request;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductReq {

    @NotBlank
    private String name;

    private String description;

    @Min(value = 0)
    @Max(value = 9999999)
    private BigDecimal price;

    @NotBlank
    private String supplier;
}
