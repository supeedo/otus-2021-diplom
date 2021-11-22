package ru.shop.service;

import ru.shop.domain.*;

import java.util.List;

public class TestConfigurationData {

     static final UserAddressDTO FIRST_TEST_ADDRESS =
            new UserAddressDTO(1L, "Moscow", "Sudostroitelnaya street", "12/1", 5, "112");
     static final UserAddressDTO SECOND_TEST_ADDRESS =
            new UserAddressDTO(2L, "New-York", "Prospect Ave", "581", 1, "5C");
     static final UserAddressDTO BAD_TEST_ADDRESS =
            new UserAddressDTO(3L, "Novosibirsk", "Lenina street", "321", 13, "456");
     static final UserAddressDTO FIRST_TEST_ADDRESS_FOR_UPDATE =
            new UserAddressDTO(1L, "Update city", "Update street", "Update house number", 987, "update number");

     static final RoleDTO FIRST_TEST_ROLE = new RoleDTO(1L, "USER");
     static final RoleDTO SECOND_TEST_ROLE = new RoleDTO(2L, "ADMIN");
     static final RoleDTO BAD_TEST_ROLE = new RoleDTO(3L, "BAD_MAN");
     static final RoleDTO FIRST_TEST_ROLE_FOR_UPDATE = new RoleDTO(1L, "UPDATE_USER");

     static final StatusOrderDTO FIRST_TEST_STATUS = new StatusOrderDTO(1L, "REGISTRATION");
     static final StatusOrderDTO SECOND_TEST_STATUS = new StatusOrderDTO(2L, "DELIVERY");
     static final StatusOrderDTO BAD_TEST_STATUS = new StatusOrderDTO(3L, "BAD_STATUS");
     static final StatusOrderDTO FIRST_TEST_STATUS_FOR_UPDATE = new StatusOrderDTO(1L, "CLOSED");

     static final ProductDTO FIRST_TEST_PRODUCT = new ProductDTO(1L,
            new CategoryDTO(1L, "laptop", "description for laptop category"),
            "Lenovo", "lenovo laptop", 120.0, true);
     static final ProductDTO SECOND_TEST_PRODUCT = new ProductDTO(2L,
            new CategoryDTO(1L, "laptop", "description for laptop category"),
            "Macbook pro", "Apple laptop 16\"", 250.0, true);
     static final ProductDTO BAD_TEST_PRODUCT = new ProductDTO(3L,
            new CategoryDTO(2L, "desktop", "description for desktop category"),
            "bad product", "bad description", 999.99, false);
     static final ProductDTO FIRST_TEST_PRODUCT_FOR_UPDATE = new ProductDTO(1L,
            new CategoryDTO(2L, "desktop", "description for desktop category"),
            "update product", "update description", 12345.12, false);

     static final UserInformationDTO FIRST_TEST_USER_INFORMATION =
            new UserInformationDTO(1L, "Ivanov", "Ivan", "Ivanovich", "88001231212", FIRST_TEST_ADDRESS);
     static final UserInformationDTO SECOND_TEST_USER_INFORMATION =
            new UserInformationDTO(2L, "Jimmy", "Alish", null, "88003213232", FIRST_TEST_ADDRESS);
     static final UserInformationDTO BAD_TEST_USER_INFORMATION =
            new UserInformationDTO(3L, "Bad First Name", "Bad Last Name", "Bad patronymic", "9998887766", BAD_TEST_ADDRESS);
     static final UserInformationDTO FIRST_TEST_USER_INFORMATION_FOR_UPDATE =
            new UserInformationDTO(1L, "Update first name", "Update last name", "Update patronymic", "0009998877", FIRST_TEST_ADDRESS_FOR_UPDATE);


     static final UserDTO FIRST_TEST_USER =
            new UserDTO(1L, "test@test-host.ru", "qwerty", FIRST_TEST_USER_INFORMATION, FIRST_TEST_ROLE, true);
     static final UserDTO SECOND_TEST_USER =
            new UserDTO(2L, "test2@test-host.ru", "ytrewq", SECOND_TEST_USER_INFORMATION, FIRST_TEST_ROLE, true);
     static final UserDTO BAD_TEST_USER =
            new UserDTO(3L, "bad-test@bad-test-host.ru", "badpassword", BAD_TEST_USER_INFORMATION, BAD_TEST_ROLE, false);
     static final UserDTO FIRST_TEST_USER_FOR_UPDATE =
            new UserDTO(1L, "update@update-host.ru", "update", FIRST_TEST_USER_INFORMATION_FOR_UPDATE, FIRST_TEST_ROLE_FOR_UPDATE, false);

     static final OrderDTO FIRST_TEST_ORDER = new OrderDTO(
             1L,
             "call in an hour",
             FIRST_TEST_STATUS,
             FIRST_TEST_USER,
             List.of(FIRST_TEST_PRODUCT));
     static final OrderDTO SECOND_TEST_ORDER = new OrderDTO(
             2L,
             "call in an hour",
             FIRST_TEST_STATUS,
             FIRST_TEST_USER,
             List.of(FIRST_TEST_PRODUCT, SECOND_TEST_PRODUCT));
     static final OrderDTO BAD_TEST_ORDER = new OrderDTO(
             3L,
             "bad test node",
             BAD_TEST_STATUS,
             BAD_TEST_USER,
             List.of(FIRST_TEST_PRODUCT));
     static final OrderDTO FIRST_TEST_ORDER_FOR_UPDATE = new OrderDTO(
             1L,
             "update test node",
             FIRST_TEST_STATUS_FOR_UPDATE,
             FIRST_TEST_USER_FOR_UPDATE,
             List.of(FIRST_TEST_PRODUCT_FOR_UPDATE, FIRST_TEST_PRODUCT_FOR_UPDATE));

}
