package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static ru.shop.service.TestData.*;

@SpringBootTest
@DisplayName("User service tests")
class UserServiceImplTest {

    private final UserService service;
    private static final Long COUNT_TEST_USER = 2L;
    private static final Long BAD_COUNT_TEST_USER = 3L;


    UserServiceImplTest(@Qualifier("userServiceImpl") UserService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count user as expected")
    void getCountUser() {
        final Long actualCountRoles = service.getCountUser();
        assertThat(actualCountRoles)
                .isNotNull()
                .isEqualTo(COUNT_TEST_USER)
                .isNotEqualTo(BAD_COUNT_TEST_USER);
    }

    @Test
    @DisplayName("Getting all users as expected")
    void getAllUser() {
        final var actualUsers = service.getAllUser();
        assertThat(actualUsers)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_USER))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_USER))
                .containsOnly(FIRST_TEST_USER, SECOND_TEST_USER)
                .doesNotContain(BAD_TEST_USER);
    }

    @Test
    @DisplayName("Getting user by id as expected")
    void getUserById() {
        final var actualUser = service.getUserById(FIRST_TEST_USER.getId());
        assertThat(actualUser)
                .isNotNull()
                .isEqualTo(FIRST_TEST_USER)
                .isNotEqualTo(SECOND_TEST_USER)
                .isNotEqualTo(BAD_TEST_USER);
    }

    @Test
    @DisplayName("Deleting an user by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteUserById() {
        final var actualUser = service.getUserById(SECOND_TEST_USER.getId());
        assertThat(actualUser)
                .isNotNull()
                .isEqualTo(SECOND_TEST_USER)
                .isNotEqualTo(FIRST_TEST_USER)
                .isNotEqualTo(BAD_TEST_ROLE);
        service.deleteUserById(SECOND_TEST_USER.getId());
        final var thrown = catchThrowable(() ->
                service.getUserById(SECOND_TEST_USER.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new user happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewUser() {
        final var actualUsers = service.getAllUser();
        assertThat(actualUsers)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_USER))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_USER))
                .containsOnly(FIRST_TEST_USER, SECOND_TEST_USER)
                .doesNotContain(BAD_TEST_USER);
        service.createNewUser(BAD_TEST_USER);
        final var actualUsersAfterCreate = service.getAllUser();
        assertThat(actualUsersAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_USER))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_USER))
                .containsOnly(FIRST_TEST_USER, SECOND_TEST_USER, BAD_TEST_USER);
    }

    @Test
    @DisplayName("Update user as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateUser() {
        final var actualUser = service.getUserById(FIRST_TEST_USER.getId());
        assertThat(actualUser)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_USER.getId())
                .hasFieldOrPropertyWithValue("email", FIRST_TEST_USER.getEmail())
                .hasFieldOrPropertyWithValue("password", FIRST_TEST_USER.getPassword())
                .hasFieldOrPropertyWithValue("userInformation", FIRST_TEST_USER.getUserInformation())
                .hasFieldOrPropertyWithValue("active", FIRST_TEST_USER.isActive())
                .hasFieldOrPropertyWithValue("role", FIRST_TEST_USER.getRole());
        service.updateUser(FIRST_TEST_USER_FOR_UPDATE);
        final var actualUserAfterUpdate = service.getUserById(FIRST_TEST_ROLE.getId());
        assertThat(actualUserAfterUpdate)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_USER_FOR_UPDATE.getId())
                .hasFieldOrPropertyWithValue("email", FIRST_TEST_USER_FOR_UPDATE.getEmail())
                .hasFieldOrPropertyWithValue("password", FIRST_TEST_USER_FOR_UPDATE.getPassword())
                .hasFieldOrPropertyWithValue("userInformation", FIRST_TEST_USER_FOR_UPDATE.getUserInformation())
                .hasFieldOrPropertyWithValue("active", FIRST_TEST_USER_FOR_UPDATE.isActive())
                .hasFieldOrPropertyWithValue("role", FIRST_TEST_USER_FOR_UPDATE.getRole());

    }
}