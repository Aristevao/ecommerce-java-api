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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public List<ProductResponse> findAllProducts() {
        return findUseCase.findAll().stream()
                .map(productMapper::toResponse)
                .map(product -> product.add(linkTo(
                        methodOn(ProductController.class).findProductById(product.getId())).withSelfRel()))
                .collect(Collectors.toList());
    }

//    HateOAS with CollectionModel class
//    @GetMapping
//    CollectionModel<EntityModel<ProductResponse>> findAllProducts() {
//        List<EntityModel<ProductResponse>> employees = findUseCase.findAll().stream()
//                .map(productMapper::toResponse)
//                .map(product -> EntityModel.of(product,
//                        linkTo(methodOn(ProductController.class).findProductById(product.getId())).withSelfRel()))
//                .collect(Collectors.toList());
//        return CollectionModel.of(employees, linkTo(methodOn(ProductController.class).findAllProducts()).withSelfRel());
//    }

    @GetMapping("{productId}")
    public ProductResponse findProductById(@PathVariable(name = "productId") final Integer id) {
        return productMapper.toResponse(findUseCase.findById(id))
                .add(linkTo(ProductController.class).slash(id).withSelfRel())
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withRel("updateProduct"))
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withRel("deleteProduct"))
                .add(linkTo(methodOn(ProductController.class).findAllProducts()).withRel("allProducts"));
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
