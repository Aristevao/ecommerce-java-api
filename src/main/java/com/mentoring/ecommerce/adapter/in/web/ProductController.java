package com.mentoring.ecommerce.adapter.in.web;

import com.mentoring.ecommerce.adapter.in.web.mapper.ProductMapper;
import com.mentoring.ecommerce.adapter.in.web.request.ProductRequest;
import com.mentoring.ecommerce.adapter.in.web.response.ProductResponse;
import com.mentoring.ecommerce.application.port.in.DeleteProductUserCase;
import com.mentoring.ecommerce.application.port.in.FindProductUserCase;
import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.in.UpdateProductUserCase;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final SaveProductUseCase saveUseCase;

    private final FindProductUserCase findUseCase;

    private final UpdateProductUserCase updateUseCase;

    private final DeleteProductUserCase deleteUseCase;

    private final ProductMapper productMapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductResponse saveProduct(@Valid @RequestBody final ProductRequest request) {
        Product product = saveUseCase.saveProduct(productMapper.toDomain(request));
        return productMapper.toResponse(product)
                .add(linkTo(methodOn(ProductController.class).findProductById(product.getId())).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).findAllProducts()).withRel("products"));
    }

    @GetMapping
    public List<ProductResponse> findAllProducts() {
        return findUseCase.findAll().stream()
                .map(productMapper::toResponse)
                .map(product -> product.add(linkTo(
                        methodOn(ProductController.class).findProductById(product.getId())).withSelfRel()))
                .collect(Collectors.toList());
    }

    @GetMapping("{productId}")
    public ProductResponse findProductById(@PathVariable(name = "productId") final Integer id) {
        return productMapper.toResponse(findUseCase.findById(id))
                .add(linkTo(ProductController.class).slash(id).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withRel("update"))
                .add(linkTo(methodOn(ProductController.class).deleteProduct(id)).withRel("delete"))
                .add(linkTo(methodOn(ProductController.class).findAllProducts()).withRel("products"));
    }

    @PutMapping("{productId}")
    public ProductResponse updateProduct(
            @RequestBody final ProductRequest request,
            @PathVariable(name = "productId") final Integer id) {
        Product product = updateUseCase.updateProduct(productMapper.toDomain(request), id);
        return productMapper.toResponse(product)
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).findAllProducts()).withRel("products"));
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId") Integer id) {
        deleteUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
