package com.mentoring.ecommerce.adapter.in.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import com.mentoring.common.pagination.PageBuilder;
import com.mentoring.ecommerce.adapter.in.web.dto.ProductDTO;
import com.mentoring.ecommerce.adapter.in.web.mapper.ProductMapper;
import com.mentoring.ecommerce.application.port.in.product.DeleteProductUserCase;
import com.mentoring.ecommerce.application.port.in.product.FindProductUserCase;
import com.mentoring.ecommerce.application.port.in.product.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.in.product.UpdateProductUserCase;
import com.mentoring.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
    public ProductDTO saveProduct(@Valid @RequestBody final ProductDTO request) {
        final Product product = saveUseCase.save(productMapper.toEntity(request));
        return productMapper.toDto(product)
                .add(linkTo(methodOn(ProductController.class).findProductById(product.getId())).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).findAllProducts(new PageBuilder().build())).withRel("products"));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllProducts(Pageable pageable) {
        final Page<Product> products = findUseCase.findAll(pageable);
        final Page<ProductDTO> response = productMapper.toDto(products);
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("{productId}")
    public ProductDTO findProductById(@PathVariable(name = "productId") final Integer id) {
        return productMapper.toDto(findUseCase.findById(id))
                .add(linkTo(ProductController.class).slash(id).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withRel("update"))
                .add(linkTo(methodOn(ProductController.class).deleteProduct(id)).withRel("delete"))
                .add(linkTo(methodOn(ProductController.class).findAllProducts(new PageBuilder().build())).withRel("products"));
    }

    @PutMapping("{productId}")
    public ProductDTO updateProduct(
            @RequestBody final ProductDTO request,
            @PathVariable(name = "productId") final Integer id) {
        final Product product = updateUseCase.update(productMapper.toEntity(request), id);
        return productMapper.toDto(product)
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).findAllProducts(new PageBuilder().build())).withRel("products"));
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId") final Integer id) {
        deleteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
