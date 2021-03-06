package com.mentoring.ecommerce.application.service.product;

import com.mentoring.ecommerce.application.port.in.DeleteProductUserCase;
import com.mentoring.ecommerce.application.port.out.DeleteProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductDeleteService implements DeleteProductUserCase {

    private final ProductFindService productFindService;

    private final DeleteProductPort port;

    @Override
    public void deleteProduct(final Integer id) {
        productFindService.findById(id);
        port.deleteProduct(id);
    }
}
