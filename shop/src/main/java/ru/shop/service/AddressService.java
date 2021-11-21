package ru.shop.service;

import ru.shop.domain.UserAddressDTO;

import java.util.List;

public interface AddressService {
    Long getCountAddress();

    List<UserAddressDTO> getAllAddress();

    UserAddressDTO getAddressById(Long categoryId);

    void deleteAddressById(Long categoryId);

    void createNewAddress(UserAddressDTO categoryDto);

    void updateAddress(UserAddressDTO categoryDto);
}
