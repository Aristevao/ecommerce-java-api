package com.mentoring.ecommerce.adapter.in.web.mapper;

import com.mentoring.ecommerce.adapter.in.web.request.ProductRequest;
import com.mentoring.ecommerce.adapter.in.web.response.ProductResponse;
import com.mentoring.ecommerce.domain.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface ProductMapper {

    Product toDomain(final ProductRequest request);
    ProductResponse toResponse(final Product product);
}
