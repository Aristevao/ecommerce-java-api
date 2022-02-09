package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.mapper.ProductMapper;
import com.mentoring.ecommerce.adapter.in.web.request.ProductReq;
import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private SaveProductUseCase saveUseCase;
    private ProductMapper productMapper;

    @PostMapping()
    public void saveProduct(ProductReq request) {
        saveUseCase.saveProduct(productMapper.toDomain(request));
    }
}
