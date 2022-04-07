package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.mapper.ProductMapper;
import com.mentoring.ecommerce.adapter.in.web.request.ProductRequest;
import com.mentoring.ecommerce.adapter.in.web.response.ProductResponse;
import com.mentoring.ecommerce.application.port.in.DeleteProductUserCase;
import com.mentoring.ecommerce.application.port.in.FindProductUserCase;
import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.in.UpdateProductUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final SaveProductUseCase saveUseCase;

    private final FindProductUserCase findUseCase;

    private final UpdateProductUserCase updateUseCase;

    private final DeleteProductUserCase deleteUseCase;

    private final ProductMapper productMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@Valid @RequestBody final ProductRequest request) {
        saveUseCase.saveProduct(productMapper.toDomain(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok().body(findUseCase.findAll().stream()
                .map(productMapper::toResponse)
                .toList());
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable(name = "productId") final Integer id) {
        return ResponseEntity.ok().body(productMapper.toResponse(findUseCase.findById(id)));
    }

    @PutMapping("{productId}")
    public void updateProduct(
            @RequestBody final ProductRequest request,
            @PathVariable(name = "productId") final Integer id) {

        updateUseCase.updateProduct(productMapper.toDomain(request), id);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "productId") Integer id) {
        deleteUseCase.deleteProduct(id);
    }
}
