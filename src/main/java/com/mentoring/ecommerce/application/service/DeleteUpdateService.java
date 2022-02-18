package com.mentoring.ecommerce.application.service;

import com.mentoring.ecommerce.application.port.in.DeleteProductUserCase;
import com.mentoring.ecommerce.application.port.out.DeleteProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteUpdateService implements DeleteProductUserCase {

    @Autowired
    ProductFindService productFindService;

    @Autowired
    DeleteProductPort port;

    @Override
    public void deleteProduct(final Integer id) {
        productFindService.findById(id);
        port.deleteProduct(id);
    }
}
