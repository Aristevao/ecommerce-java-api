package com.mentoring.ecommerce.adapter.in.web.mapper;

import com.mentoring.ecommerce.adapter.in.web.dto.ProductDTO;
import com.mentoring.ecommerce.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends CommonMapper<Product, ProductDTO> {
}
