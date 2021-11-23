package ru.shop.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shop.domain.Order;

import java.util.List;

@SpringBootTest
class OrderServiceImplTest {

    private final OrderServiceImpl orderService;

    @Autowired
    OrderServiceImplTest(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Test
    void getAll() {
        List<Order> serviceAll = orderService.getAll();
        serviceAll.forEach(System.out::println);
    }
}