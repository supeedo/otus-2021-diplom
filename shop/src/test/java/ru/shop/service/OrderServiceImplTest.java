package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static ru.shop.service.TestConfigurationData.*;

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
        assertThat(actualOrders)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_ORDER))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ORDER))
                .containsOnly(FIRST_TEST_ORDER, SECOND_TEST_ORDER)
                .doesNotContain(BAD_TEST_ORDER);
    }

    @Test
    @DisplayName("Getting order by id as expected")
    void getOrderById() {
        final var actualOrder = service.getOrderById(FIRST_TEST_ORDER.getId());
        assertThat(actualOrder)
                .isNotNull()
                .isEqualTo(FIRST_TEST_ORDER)
                .isNotEqualTo(SECOND_TEST_ORDER)
                .isNotEqualTo(BAD_TEST_ORDER);
    }

    @Test
    @DisplayName("Deleting an order by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteOrderById() {
        final var actualOrder = service.getOrderById(FIRST_TEST_ORDER.getId());
        assertThat(actualOrder)
                .isNotNull()
                .isEqualTo(FIRST_TEST_ORDER)
                .isNotEqualTo(SECOND_TEST_ORDER)
                .isNotEqualTo(BAD_TEST_ORDER);
        service.deleteOrderById(FIRST_TEST_ORDER.getId());
        final var thrown = catchThrowable(() ->
                service.getOrderById(FIRST_TEST_ORDER.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new order happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewOrder() {
        final var actualOrders = service.getAllOrder();
        assertThat(actualOrders)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_ORDER))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ORDER))
                .containsOnly(FIRST_TEST_ORDER, SECOND_TEST_ORDER)
                .doesNotContain(BAD_TEST_ORDER);
        service.createNewOrder(BAD_TEST_ORDER);
        final var actualOrdersAfterCreate = service.getAllOrder();
        assertThat(actualOrdersAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_ORDER))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_ORDER))
                .containsOnly(FIRST_TEST_ORDER, SECOND_TEST_ORDER, BAD_TEST_ORDER);
    }

    @Test
    @DisplayName("Update order as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateOrder() {
    }
}