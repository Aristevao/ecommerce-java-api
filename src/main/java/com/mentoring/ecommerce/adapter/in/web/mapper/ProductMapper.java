package com.mentoring.ecommerce.adapter.in.web.mapper;

import com.mentoring.ecommerce.adapter.in.web.request.ProductRequest;
import com.mentoring.ecommerce.adapter.in.web.response.ProductResponse;
import com.mentoring.ecommerce.domain.Product;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(final ProductRequest request);
    ProductResponse toResponse(final Product product);

    default Page<ProductResponse> allToResponse(Page<Product> products) {
        return products.map(this::toResponse);
    }
}
