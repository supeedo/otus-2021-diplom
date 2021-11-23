package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static ru.shop.service.TestData.*;

@SpringBootTest
@DisplayName("Status order service tests")
class StatusOrderServiceImplTest {

    private final StatusOrderService service;

    private static final Long COUNT_TEST_STATUS = 2L;
    private static final Long BAD_COUNT_TEST_STATUS = 3L;

    StatusOrderServiceImplTest(@Qualifier("statusOrderServiceImpl")StatusOrderService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count status order as expected")
    void getCountStatusOrder() {
        final Long actualCountStatuses = service.getCountStatusOrder();
        assertThat(actualCountStatuses)
                .isNotNull()
                .isEqualTo(COUNT_TEST_STATUS)
                .isNotEqualTo(BAD_COUNT_TEST_STATUS);
    }

    @Test
    @DisplayName("Getting all status order as expected")
    void getAllStatusOrder() {
        final var actualStatus = service.getAllStatusOrder();
        assertThat(actualStatus)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_STATUS))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_STATUS))
                .containsOnly(FIRST_TEST_STATUS, SECOND_TEST_STATUS)
                .doesNotContain(BAD_TEST_STATUS);
    }

    @Test
    @DisplayName("Getting status order by id as expected")
    void getStatusOrderById() {
        final var actualStatus = service.getStatusOrderById(FIRST_TEST_STATUS.getId());
        assertThat(actualStatus)
                .isNotNull()
                .isEqualTo(FIRST_TEST_STATUS)
                .isNotEqualTo(SECOND_TEST_STATUS)
                .isNotEqualTo(BAD_TEST_STATUS);
    }

    @Test
    @DisplayName("Deleting an status order by id works as expected")
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
    }

    @Test
    @DisplayName("Deleting an status order by id if role use by users")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteRoleByIdIfUses() {
//        final var thrownForReferencesKey = catchThrowable(() ->
//                service.deleteStatusOrderById(FIRST_TEST_STATUS.getId())
//        );
//        assertThat(thrownForReferencesKey)
//                .isInstanceOf(DataIntegrityViolationException.class)
//                .isNotInstanceOf(EntityNotFoundException.class)
//                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new status order happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewStatusOrder() {
        final var actualStatus = service.getAllStatusOrder();
        assertThat(actualStatus)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_STATUS))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_STATUS))
                .containsOnly(FIRST_TEST_STATUS, SECOND_TEST_STATUS)
                .doesNotContain(BAD_TEST_STATUS);
        service.createNewStatusOrder(BAD_TEST_STATUS);
        final var actualStatusAfterCreate = service.getAllStatusOrder();
        assertThat(actualStatusAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_STATUS))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_STATUS))
                .containsOnly(FIRST_TEST_STATUS, SECOND_TEST_STATUS, BAD_TEST_STATUS);
    }

    @Test
    @DisplayName("Update status order as expected")
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