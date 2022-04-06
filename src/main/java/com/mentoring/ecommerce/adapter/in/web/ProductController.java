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
    /*
     * TODO
     *   Save returns ProductResponse OK
     *   Update returns ProductResponse OK
     *   Maybe: delete returns selfLink
     *   FindById invokes Update and Delete links
     * */

    private final SaveProductUseCase saveUseCase;

    private final FindProductUserCase findUseCase;

    private final UpdateProductUserCase updateUseCase;

    private final DeleteProductUserCase deleteUseCase;

    private final ProductMapper productMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
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
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withRel("update"))
                .add(linkTo(methodOn(ProductController.class).findProductById(id)).withRel("delete"))
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "productId") Integer id) {
        deleteUseCase.deleteProduct(id);
    }
}
