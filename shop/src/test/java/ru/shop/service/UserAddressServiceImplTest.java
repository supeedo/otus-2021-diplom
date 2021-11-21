package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.shop.domain.UserAddressDTO;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
@DisplayName("Address service tests")
class UserAddressServiceImplTest {

    private final AddressService service;

    private static final Long COUNT_TEST_ADDRESS = 2L;
    private static final Long BAD_COUNT_TEST_ADDRESS = 3L;
    private static final UserAddressDTO FIRST_TEST_ADDRESS =
            new UserAddressDTO(1L, "Moscow", "Sudostroitelnaya street", "12/1", 5, "112");
    private static final UserAddressDTO SECOND_TEST_ADDRESS =
            new UserAddressDTO(2L, "New-York", "Prospect Ave", "581", 1, "5C");
    private static final UserAddressDTO BAD_TEST_ADDRESS =
            new UserAddressDTO(3L, "Novosibirsk", "Lenina street", "321", 13, "456");
    private static final UserAddressDTO FIRST_TEST_ADDRESS_FOR_UPDATE =
            new UserAddressDTO(1L, "Update city", "Update street", "Update house number", 987, "update number");

    UserAddressServiceImplTest(@Qualifier("addressServiceImpl")AddressService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count address as expected")
    void getCountAddress() {
        final Long actualCountRoles = service.getCountAddress();
        assertThat(actualCountRoles)
                .isNotNull()
                .isEqualTo(COUNT_TEST_ADDRESS)
                .isNotEqualTo(BAD_COUNT_TEST_ADDRESS);
    }

    @Test
    @DisplayName("Getting all addresses as expected")
    void getAllAddress() {
        final var actualAddresses = service.getAllAddress();
        assertThat(actualAddresses)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_ADDRESS))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ADDRESS))
                .containsOnly(FIRST_TEST_ADDRESS, SECOND_TEST_ADDRESS)
                .doesNotContain(BAD_TEST_ADDRESS);
    }

    @Test
    @DisplayName("Getting address by id as expected")
    void getAddressById() {
        final var actualAddress = service.getAddressById(FIRST_TEST_ADDRESS.getId());
        assertThat(actualAddress)
                .isNotNull()
                .isEqualTo(FIRST_TEST_ADDRESS)
                .isNotEqualTo(SECOND_TEST_ADDRESS)
                .isNotEqualTo(BAD_TEST_ADDRESS);
    }

    @Test
    @DisplayName("Deleting an address by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteAddressById() {
        final var actualAddress = service.getAddressById(FIRST_TEST_ADDRESS.getId());
        assertThat(actualAddress)
                .isNotNull()
                .isEqualTo(FIRST_TEST_ADDRESS)
                .isNotEqualTo(SECOND_TEST_ADDRESS)
                .isNotEqualTo(BAD_TEST_ADDRESS);
        service.deleteAddressById(FIRST_TEST_ADDRESS.getId());
        final var thrown = catchThrowable(() ->
                service.getAddressById(FIRST_TEST_ADDRESS.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new address happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewAddress() {
        final var actualAddress = service.getAllAddress();
        assertThat(actualAddress)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_ADDRESS))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_ADDRESS))
                .containsOnly(FIRST_TEST_ADDRESS, SECOND_TEST_ADDRESS)
                .doesNotContain(BAD_TEST_ADDRESS);
        service.createNewAddress(BAD_TEST_ADDRESS);
        final var actualAddressAfterCreate = service.getAllAddress();
        assertThat(actualAddressAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_ADDRESS))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_ADDRESS))
                .containsOnly(FIRST_TEST_ADDRESS, SECOND_TEST_ADDRESS, BAD_TEST_ADDRESS);
    }

    @Test
    @DisplayName("Update address as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateAddress() {
        final var actualAddress = service.getAddressById(FIRST_TEST_ADDRESS.getId());
        assertThat(actualAddress)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_ADDRESS.getId())
                .hasFieldOrPropertyWithValue("city", FIRST_TEST_ADDRESS.getCity())
                .hasFieldOrPropertyWithValue("street", FIRST_TEST_ADDRESS.getStreet())
                .hasFieldOrPropertyWithValue("houseNumber", FIRST_TEST_ADDRESS.getHouseNumber())
                .hasFieldOrPropertyWithValue("floor", FIRST_TEST_ADDRESS.getFloor())
                .hasFieldOrPropertyWithValue("apartmentNumber", FIRST_TEST_ADDRESS.getApartmentNumber());
        service.updateAddress(FIRST_TEST_ADDRESS_FOR_UPDATE);
        final var actualAddressAfterUpdate = service.getAddressById(FIRST_TEST_ADDRESS.getId());
        assertThat(actualAddressAfterUpdate)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_ADDRESS_FOR_UPDATE.getId())
                .hasFieldOrPropertyWithValue("city", FIRST_TEST_ADDRESS_FOR_UPDATE.getCity())
                .hasFieldOrPropertyWithValue("street", FIRST_TEST_ADDRESS_FOR_UPDATE.getStreet())
                .hasFieldOrPropertyWithValue("houseNumber", FIRST_TEST_ADDRESS_FOR_UPDATE.getHouseNumber())
                .hasFieldOrPropertyWithValue("floor", FIRST_TEST_ADDRESS_FOR_UPDATE.getFloor())
                .hasFieldOrPropertyWithValue("apartmentNumber", FIRST_TEST_ADDRESS_FOR_UPDATE.getApartmentNumber());
    }
}