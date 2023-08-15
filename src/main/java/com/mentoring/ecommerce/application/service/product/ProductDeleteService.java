package com.mentoring.ecommerce.application.service.product;

import com.mentoring.ecommerce.application.port.in.product.DeleteProductUseCase;
import com.mentoring.ecommerce.application.port.out.product.DeleteProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductDeleteService implements DeleteProductUseCase {

    private final ProductFindService productFindService;

    private final DeleteProductPort port;

    @Override
    public void delete(Integer id) {
        productFindService.findById(id);
        port.deleteProduct(id);
    }
}
