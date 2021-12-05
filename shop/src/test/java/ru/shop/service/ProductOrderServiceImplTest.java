package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DisplayName("Product order service tests")
class ProductOrderServiceImplTest {
//TODO
    private final ProductOrderService service;

    @Autowired
    ProductOrderServiceImplTest(@Qualifier("productOrderServiceImpl") ProductOrderService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count product orders as expected")
    void getCountProductOrders() {
    }

    @Test
    @DisplayName("Getting all product order as expected")
    void getAllProductOrders() {
    }

    @Test
    @DisplayName("Getting product order by id as expected")
    void getProductOrderById() {
    }

    @Test
    @DisplayName("Deleting an product order by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteProductOrderById() {
    }

    @Test
    @DisplayName("Adding a new product order happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewProductOrder() {
    }

    @Test
    @DisplayName("Update product order  as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateProductOrder() {
    }

}