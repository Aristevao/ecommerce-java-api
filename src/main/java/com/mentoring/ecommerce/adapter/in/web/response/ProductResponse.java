package com.mentoring.ecommerce.adapter.in.web.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProductResponse extends RepresentationModel<ProductResponse> {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String supplier;
    private String path;
}