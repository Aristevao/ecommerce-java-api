package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.mapper.ProductMapper;
import com.mentoring.ecommerce.adapter.in.web.request.ProductRequest;
import com.mentoring.ecommerce.adapter.in.web.response.ProductResponse;
import com.mentoring.ecommerce.application.port.in.FindProductUserCase;
import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.in.UpdateProductUserCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private SaveProductUseCase saveUseCase;
    private FindProductUserCase findUseCase;
    private UpdateProductUserCase updateUseCase;
    private ProductMapper productMapper;

    @PostMapping
    public void saveProduct(final ProductRequest request) {
        saveUseCase.saveProduct(productMapper.toDomain(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok().body(findUseCase.findAll().stream()
                .map(product -> productMapper.toResponse(product))
                .toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> findProductById(final Integer id) {
        return ResponseEntity.ok().body(productMapper.toResponse(findUseCase.findById(id)));
    }

    @PutMapping("{id}")
    public void updateProduct(final ProductRequest request, final Integer id) {
        updateUseCase.updateProduct(productMapper.toDomain(request), id);
    }
}
