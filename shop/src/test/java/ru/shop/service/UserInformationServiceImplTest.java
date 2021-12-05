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
@DisplayName("User information service tests")
class UserInformationServiceImplTest {

    private final UserInformationService service;

    private static final Long COUNT_TEST_INFORMATION = 2L;
    private static final Long BAD_COUNT_TEST_INFORMATION = 3L;

    UserInformationServiceImplTest(@Qualifier("userInformationServiceImpl") UserInformationService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count user information as expected")
    void getCountUserInformation() {
        final Long actualCountInformations = service.getCountUserInformation();
        assertThat(actualCountInformations)
                .isNotNull()
                .isEqualTo(COUNT_TEST_INFORMATION)
                .isNotEqualTo(BAD_COUNT_TEST_INFORMATION);
    }

    @Test
    @DisplayName("Getting all user informations as expected")
    void getAllUserInformation() {
        final var actualInformations = service.getAllUserInformation();
        assertThat(actualInformations)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_INFORMATION))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_INFORMATION))
                .containsOnly(FIRST_TEST_USER_INFORMATION, SECOND_TEST_USER_INFORMATION)
                .doesNotContain(BAD_TEST_USER_INFORMATION);
    }

    @Test
    @DisplayName("Getting user information by id as expected")
    void getUserInformationById() {
        final var actualInformation = service.getUserInformationById(FIRST_TEST_USER_INFORMATION.getId());
        assertThat(actualInformation)
                .isNotNull()
                .isEqualTo(FIRST_TEST_USER_INFORMATION)
                .isNotEqualTo(SECOND_TEST_USER_INFORMATION)
                .isNotEqualTo(BAD_TEST_USER_INFORMATION);
    }

    @Test
    @DisplayName("Deleting an user information by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteUserInformationById() {
        final var actualInformation = service.getUserInformationById(SECOND_TEST_USER_INFORMATION.getId());
        assertThat(actualInformation)
                .isNotNull()
                .isEqualTo(SECOND_TEST_USER_INFORMATION)
                .isNotEqualTo(FIRST_TEST_USER_INFORMATION)
                .isNotEqualTo(BAD_TEST_USER_INFORMATION);
        service.deleteUserInformationById(SECOND_TEST_USER_INFORMATION.getId());
        final var thrown = catchThrowable(() ->
                service.getUserInformationById(SECOND_TEST_USER_INFORMATION.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new user information happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewUserInformation() {
        final var actualInformations = service.getAllUserInformation();
        assertThat(actualInformations)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_INFORMATION))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_INFORMATION))
                .containsOnly(FIRST_TEST_USER_INFORMATION, SECOND_TEST_USER_INFORMATION)
                .doesNotContain(BAD_TEST_USER_INFORMATION);
        service.createNewUserInformation(BAD_TEST_USER_INFORMATION);
        final var actualUserInformationsAfterCreate = service.getAllUserInformation();
        assertThat(actualUserInformationsAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_INFORMATION))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_INFORMATION))
                .containsOnly(FIRST_TEST_USER_INFORMATION, SECOND_TEST_USER_INFORMATION, BAD_TEST_USER_INFORMATION);
    }

    @Test
    @DisplayName("Update user information as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateUserInformation() {
        final var actualInformation = service.getUserInformationById(FIRST_TEST_USER_INFORMATION.getId());
        assertThat(actualInformation)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_USER_INFORMATION.getId())
                .hasFieldOrPropertyWithValue("firstName", FIRST_TEST_USER_INFORMATION.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", FIRST_TEST_USER_INFORMATION.getLastName())
                .hasFieldOrPropertyWithValue("patronymic", FIRST_TEST_USER_INFORMATION.getPatronymic())
                .hasFieldOrPropertyWithValue("phone", FIRST_TEST_USER_INFORMATION.getPhone())
                .hasFieldOrPropertyWithValue("address", FIRST_TEST_USER_INFORMATION.getAddress());
        service.updateUserInformation(FIRST_TEST_USER_INFORMATION_FOR_UPDATE);
        final var actualInformationAfterUpdate = service.getUserInformationById(FIRST_TEST_USER_INFORMATION.getId());
        assertThat(actualInformationAfterUpdate)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_USER_INFORMATION_FOR_UPDATE.getId())
                .hasFieldOrPropertyWithValue("firstName", FIRST_TEST_USER_INFORMATION_FOR_UPDATE.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", FIRST_TEST_USER_INFORMATION_FOR_UPDATE.getLastName())
                .hasFieldOrPropertyWithValue("patronymic", FIRST_TEST_USER_INFORMATION_FOR_UPDATE.getPatronymic())
                .hasFieldOrPropertyWithValue("phone", FIRST_TEST_USER_INFORMATION_FOR_UPDATE.getPhone())
                .hasFieldOrPropertyWithValue("address", FIRST_TEST_USER_INFORMATION_FOR_UPDATE.getAddress());
    }
}