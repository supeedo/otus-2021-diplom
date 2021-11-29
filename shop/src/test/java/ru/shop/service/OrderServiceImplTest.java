package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static ru.shop.service.TestData.*;

@SpringBootTest
@DisplayName("Order service tests")
class OrderServiceImplTest {

    private static final Long COUNT_TEST_ORDER = 2L;
    private static final Long BAD_COUNT_TEST_ORDER = 3L;



    private final OrderService orderService;

    OrderServiceImplTest(@Qualifier("orderServiceImpl") OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    @DisplayName("Getting count orders as expected")
    void getCountOrder() {
        final var actualCountRoles = orderService.getCountOrder();
        assertThat(actualCountRoles)
                .isNotNull()
                .isEqualTo(COUNT_TEST_ORDER)
                .isNotEqualTo(BAD_COUNT_TEST_ORDER);
    }

    @Test
    @DisplayName("Getting all orders as expected")
    void getAllOrder() {
        final var actualOrders = orderService.getAllOrder();
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
        final var actualOrder = orderService.getOrderById(FIRST_TEST_ORDER.getId());
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
        final var actualOrder = orderService.getOrderById(SECOND_TEST_ORDER.getId());
        assertThat(actualOrder)
                .isNotNull()
                .isEqualTo(SECOND_TEST_ORDER)
                .isNotEqualTo(FIRST_TEST_ORDER)
                .isNotEqualTo(BAD_TEST_ORDER);
        orderService.deleteOrderById(SECOND_TEST_ORDER.getId());
        final var thrown = catchThrowable(() ->
                orderService.getOrderById(SECOND_TEST_ORDER.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new order happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewOrder() {
        final var actualOrders = orderService.getAllOrder();
        assertThat(actualOrders)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_ORDER))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ORDER))
                .containsOnly(FIRST_TEST_ORDER, SECOND_TEST_ORDER)
                .doesNotContain(BAD_TEST_ORDER);
//        orderService.createNewOrder(BAD_TEST_ORDER);
//        final var actualOrdersAfterCreate = orderService.getAllOrder();
//        assertThat(actualOrdersAfterCreate)
//                .isNotNull()
//                .isNotEmpty()
//                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_ORDER))
//                .hasSize(Math.toIntExact(BAD_COUNT_TEST_ORDER))
//                .containsOnly(FIRST_TEST_ORDER, SECOND_TEST_ORDER, BAD_TEST_ORDER);
    }

    @Test
    @DisplayName("Update order as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateOrder() {
//        final var actualRole = service.getRoleById(FIRST_TEST_ROLE.getId());
//        assertThat(actualRole)
//                .isNotNull()
//                .hasFieldOrPropertyWithValue("id", FIRST_TEST_ROLE.getId())
//                .hasFieldOrPropertyWithValue("roleName", FIRST_TEST_ROLE.getRoleName());
//        service.updateRole(FIRST_TEST_ROLE_FOR_UPDATE);
//        final var actualRoleAfterUpdate = service.getRoleById(FIRST_TEST_ROLE.getId());
//        assertThat(actualRoleAfterUpdate)
//                .isNotNull()
//                .hasFieldOrPropertyWithValue("id", FIRST_TEST_ROLE_FOR_UPDATE.getId())
//                .hasFieldOrPropertyWithValue("roleName", FIRST_TEST_ROLE_FOR_UPDATE.getRoleName());
    }
}