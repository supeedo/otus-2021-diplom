package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.shop.domain.RoleDTO;
import ru.shop.domain.StatusOrderDTO;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Status orders service tests")
class StatusOrderServiceImplTest {

    private final StatusOrderService service;

    private static final Long COUNT_TEST_STATUS = 2L;
    private static final Long BAD_COUNT_TEST_STATUS = 3L;
    private static final StatusOrderDTO FIRST_TEST_STATUS = new StatusOrderDTO(1L, "REGISTRATION");
    private static final StatusOrderDTO SECOND_TEST_STATUS = new StatusOrderDTO(2L, "DELIVERY");
    private static final StatusOrderDTO BAD_TEST_STATUS = new StatusOrderDTO(3L, "BAD_STATUS");
    private static final StatusOrderDTO FIRST_TEST_STATUS_FOR_UPDATE = new StatusOrderDTO(1L, "CLOSED");

    StatusOrderServiceImplTest(@Qualifier("statusOrderServiceImpl") StatusOrderService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count statuses as expected")
    void getCountStatusOrder() {
        final Long actualCountStatuses = service.getCountStatusOrder();
        assertThat(actualCountStatuses)
                .isNotNull()
                .isEqualTo(COUNT_TEST_STATUS)
                .isNotEqualTo(BAD_COUNT_TEST_STATUS);
    }

    @Test
    @DisplayName("Getting all statuses as expected")
    void getAllStatusOrder() {
        final var actualStatuses = service.getAllStatusOrder();
        System.out.println(actualStatuses);
        assertThat(actualStatuses)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_STATUS))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_STATUS))
                .containsOnly(FIRST_TEST_STATUS, SECOND_TEST_STATUS)
                .doesNotContain(BAD_TEST_STATUS);
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