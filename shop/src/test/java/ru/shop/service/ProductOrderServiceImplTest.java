package ru.shop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.ProductOrder;

import java.util.List;

@SpringBootTest
class ProductOrderServiceImplTest {

   private final ProductOrderServiceImpl service;

   @Autowired
    ProductOrderServiceImplTest(ProductOrderServiceImpl service) {
        this.service = service;
    }

    @Test
    @Transactional(readOnly = true)
    void getAll() {
        List<ProductOrder> productOrders = service.getAll();
        productOrders.forEach(System.out::println);
    }
}