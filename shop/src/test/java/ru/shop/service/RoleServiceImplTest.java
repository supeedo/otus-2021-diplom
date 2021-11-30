package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import ru.shop.domain.RoleDTO;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static ru.shop.service.TestData.*;

@SpringBootTest
@DisplayName("Role service tests")
class RoleServiceImplTest {


    private static final Long COUNT_TEST_ROLE = 2L;
    private static final Long BAD_COUNT_TEST_ROLE = 3L;

    private final RoleService service;

    RoleServiceImplTest(@Qualifier("roleServiceImpl") RoleService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count roles as expected")
    void getCountRole() {
        final var actualCountRoles = service.getCountRole();
        assertThat(actualCountRoles)
                .isNotNull()
                .isEqualTo(COUNT_TEST_ROLE)
                .isNotEqualTo(BAD_COUNT_TEST_ROLE);
    }

    @Test
    @DisplayName("Getting all roles as expected")
    void getAllRole() {
        final var actualRoles = service.getAllRole();
        assertThat(actualRoles)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_ROLE))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ROLE))
                .containsOnly(FIRST_TEST_ROLE, SECOND_TEST_ROLE)
                .doesNotContain(BAD_TEST_ROLE);
    }

    @Test
    @DisplayName("Getting role by id as expected")
    void getRoleById() {
        final var actualRole = service.getRoleById(FIRST_TEST_ROLE.getId());
        assertThat(actualRole)
                .isNotNull()
                .isEqualTo(FIRST_TEST_ROLE)
                .isNotEqualTo(SECOND_TEST_ROLE)
                .isNotEqualTo(BAD_TEST_ROLE);
    }

    @Test
    @DisplayName("Deleting an role by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteRoleById() {
        final var actualRole = service.getRoleById(SECOND_TEST_ROLE.getId());
        assertThat(actualRole)
                .isNotNull()
                .isEqualTo(SECOND_TEST_ROLE)
                .isNotEqualTo(FIRST_TEST_ROLE)
                .isNotEqualTo(BAD_TEST_ROLE);
        service.deleteRoleById(SECOND_TEST_ROLE.getId());
        final var thrown = catchThrowable(() ->
                service.getRoleById(SECOND_TEST_ROLE.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Deleting an role by id if role use by users")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteRoleByIdIfUses() {
        final var thrownForReferencesKey = catchThrowable(() ->
                service.deleteRoleById(FIRST_TEST_ROLE.getId())
        );
        assertThat(thrownForReferencesKey)
                .isInstanceOf(DataIntegrityViolationException.class)
                .isNotInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new role happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewRole() {
        final var actualRoles = service.getAllRole();
        assertThat(actualRoles)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_ROLE))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ROLE))
                .containsOnly(FIRST_TEST_ROLE, SECOND_TEST_ROLE)
                .doesNotContain(BAD_TEST_ROLE);
        service.createNewRole(BAD_TEST_ROLE);
        final var actualRolesAfterCreate = service.getAllRole();
        assertThat(actualRolesAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_ROLE))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_ROLE))
                .containsOnly(FIRST_TEST_ROLE, SECOND_TEST_ROLE, BAD_TEST_ROLE);
    }

    @Test
    @DisplayName("Update role as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateRole() {
        final var actualRole = service.getRoleById(FIRST_TEST_ROLE.getId());
        assertThat(actualRole)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_ROLE.getId())
                .hasFieldOrPropertyWithValue("roleName", FIRST_TEST_ROLE.getRoleName());
        service.updateRole(FIRST_TEST_ROLE_FOR_UPDATE);
        final var actualRoleAfterUpdate = service.getRoleById(FIRST_TEST_ROLE.getId());
        assertThat(actualRoleAfterUpdate)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_ROLE_FOR_UPDATE.getId())
                .hasFieldOrPropertyWithValue("roleName", FIRST_TEST_ROLE_FOR_UPDATE.getRoleName());
    }
}