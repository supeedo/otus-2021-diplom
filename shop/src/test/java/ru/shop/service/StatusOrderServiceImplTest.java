package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import ru.shop.domain.RoleDTO;
import ru.shop.domain.StatusOrderDTO;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

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
    @DisplayName("Getting status by id as expected")
    void getStatusOrderById() {
        final var actualStatus = service.getStatusOrderById(FIRST_TEST_STATUS.getId());
        assertThat(actualStatus)
                .isNotNull()
                .isEqualTo(FIRST_TEST_STATUS)
                .isNotEqualTo(SECOND_TEST_STATUS)
                .isNotEqualTo(BAD_TEST_STATUS);
    }

    @Test
    @DisplayName("Deleting an status by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteStatusOrderById() {
        final var actualStatus = service.getStatusOrderById(SECOND_TEST_STATUS.getId());
        assertThat(actualStatus)
                .isNotNull()
                .isEqualTo(SECOND_TEST_STATUS)
                .isNotEqualTo(FIRST_TEST_STATUS)
                .isNotEqualTo(BAD_TEST_STATUS);
        service.deleteStatusOrderById(SECOND_TEST_STATUS.getId());
        final var thrown = catchThrowable(() ->
                service.getStatusOrderById(SECOND_TEST_STATUS.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);

        final var thrownForReferencesKey = catchThrowable(() ->
                service.deleteStatusOrderById(FIRST_TEST_STATUS.getId())
        );
        assertThat(thrownForReferencesKey)
                .isInstanceOf(DataIntegrityViolationException.class)
                .isNotInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new status happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewStatusOrder() {
        final var actualStatuses = service.getAllStatusOrder();
        assertThat(actualStatuses)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_STATUS))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_STATUS))
                .containsOnly(FIRST_TEST_STATUS, SECOND_TEST_STATUS)
                .doesNotContain(BAD_TEST_STATUS);
        service.createNewStatusOrder(BAD_TEST_STATUS);
        final var actualStatusesAfterCreate = service.getAllStatusOrder();
        assertThat(actualStatusesAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_STATUS))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_STATUS))
                .containsOnly(FIRST_TEST_STATUS, SECOND_TEST_STATUS, BAD_TEST_STATUS);
    }

    @Test
    @DisplayName("Update status as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateStatusOrder() {
        final var actualStatus = service.getStatusOrderById(FIRST_TEST_STATUS.getId());
        assertThat(actualStatus)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_STATUS.getId())
                .hasFieldOrPropertyWithValue("status", FIRST_TEST_STATUS.getStatus());
        service.updateStatusOrder(FIRST_TEST_STATUS_FOR_UPDATE);
        final var actualStatusAfterUpdate = service.getStatusOrderById(FIRST_TEST_STATUS.getId());
        assertThat(actualStatusAfterUpdate)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_STATUS_FOR_UPDATE.getId())
                .hasFieldOrPropertyWithValue("status", FIRST_TEST_STATUS_FOR_UPDATE.getStatus());
    }
}