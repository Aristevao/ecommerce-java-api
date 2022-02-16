package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.mapper.ProductMapper;
import com.mentoring.ecommerce.adapter.in.web.request.ProductReq;
import com.mentoring.ecommerce.adapter.in.web.response.ProductRes;
import com.mentoring.ecommerce.application.port.in.FindProductUserCase;
import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.in.UpdateProductUserCase;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveProduct(final ProductReq request) {
        saveUseCase.saveProduct(productMapper.toDomain(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductRes>> findAllProducts() {
        return ResponseEntity.ok().body(findUseCase.findAll().stream()
                .map(product -> productMapper.toResponse(product))
                .toList());
    }

    @PutMapping("{id}")
    public void updateProduct(final ProductReq request, final Integer id) {
        updateUseCase.updateProduct(productMapper.toDomain(request), id);
    }
}
