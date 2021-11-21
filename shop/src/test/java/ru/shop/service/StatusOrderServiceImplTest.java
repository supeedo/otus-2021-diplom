package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Status orders service tests")
class StatusOrderServiceImplTest {

    private final StatusOrderService service;

    private static final Long COUNT_TEST_STATUS = 2L;
    private static final Long BAD_COUNT_TEST_STATUS = 3L;

    StatusOrderServiceImplTest(@Qualifier("statusOrderServiceImpl") StatusOrderService service) {
        this.service = service;
    }

    @Test
    void getCountStatusOrder() {
        final Long actualCountRoles = service.getCountStatusOrder();
        assertThat(actualCountRoles)
                .isNotNull()
                .isEqualTo(COUNT_TEST_STATUS)
                .isNotEqualTo(BAD_COUNT_TEST_STATUS);
    }

    @Test
    void getAllStatusOrder() {
    }

    @Test
    void getStatusOrderById() {
    }

    @Test
    void deleteStatusOrderById() {
    }

    @Test
    void createNewStatusOrder() {
    }

    @Test
    void updateStatusOrder() {
    }
}