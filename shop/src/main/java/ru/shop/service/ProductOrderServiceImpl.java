package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.ProductOrder;
import ru.shop.repository.ProductOrderRepository;

import java.util.List;

@Service
public class ProductOrderServiceImpl {

    private final ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductOrder> getAll(){
        return productOrderRepository.findAll();
    }
}
