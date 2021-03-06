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

    @NotNull(message = "A name must be provided")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;

    @Min(value = 0, message = "Price cannot be a negative value")
    @Max(value = 9999999, message = "Price must be less than 9999999")
    private BigDecimal price;

    @NotNull
    @NotBlank
    private String supplier;

    private String fileName;

    private String filePath;
}
