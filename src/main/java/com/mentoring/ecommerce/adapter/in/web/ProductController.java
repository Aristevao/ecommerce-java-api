package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.mapper.ProductMapper;
import com.mentoring.ecommerce.adapter.in.web.request.ProductRequest;
import com.mentoring.ecommerce.adapter.in.web.response.ProductResponse;
import com.mentoring.ecommerce.application.port.in.FindProductUserCase;
import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private SaveProductUseCase saveUseCase;
    private FindProductUserCase findUseCase;
    private ProductMapper productMapper;

    @PostMapping()
    public void saveProduct(ProductRequest request) {
        saveUseCase.saveProduct(productMapper.toDomain(request));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok().body(findUseCase.findAll().stream()
                .map(product -> productMapper.toResponse(product))
                .toList());
    }
}
