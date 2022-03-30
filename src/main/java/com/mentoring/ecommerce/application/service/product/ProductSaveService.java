package com.mentoring.ecommerce.application.service.product;


import com.mentoring.ecommerce.application.port.in.SaveProductUseCase;
import com.mentoring.ecommerce.application.port.out.SaveProductPort;
import com.mentoring.ecommerce.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductSaveService implements SaveProductUseCase {

    @Autowired
    private SaveProductPort port;

    @Override
    public void saveProduct(final Product product) {
        port.saveProduct(product);
    }
}
