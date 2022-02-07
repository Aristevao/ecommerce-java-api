package com.mentoring.ecommerce.adapter.in.web.mapper;

import com.mentoring.ecommerce.adapter.in.web.request.ProductReq;
import com.mentoring.ecommerce.domain.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(ProductReq request);
    Product toResponse(ProductReq product);
}
