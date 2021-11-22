package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Order service tests")
class OrderServiceImplTest {

    private final OrderService service;

    private static final Long COUNT_TEST_ORDER = 2L;
    private static final Long BAD_COUNT_TEST_ORDER = 3L;

    OrderServiceImplTest(@Qualifier("orderServiceImpl") OrderService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count orders as expected")
    void getCountOrder() {
        final Long actualCountOrders = service.getCountOrder();
        assertThat(actualCountOrders)
                .isNotNull()
                .isEqualTo(COUNT_TEST_ORDER)
                .isNotEqualTo(BAD_COUNT_TEST_ORDER);
    }

    @Test
    @DisplayName("Getting all orders as expected")
    void getAllOrder() {
        final var actualOrders = service.getAllOrder();
        System.out.println(actualOrders);
//        assertThat(actualOrders)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSize(Math.toIntExact(COUNT_TEST_ORDER))
//                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ORDER))
//                .containsOnly(FIRST_TEST_ORDER, SECOND_TEST_ORDER)
//                .doesNotContain(BAD_TEST_ORDER);
    }

    @Test
    @DisplayName("Getting order by id as expected")
    void getOrderById() {
    }

    @Test
    @DisplayName("Deleting an order by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteOrderById() {
    }

    @Test
    @DisplayName("Adding a new order happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewOrder() {
    }

    @Test
    @DisplayName("Update order as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateOrder() {
    }
}