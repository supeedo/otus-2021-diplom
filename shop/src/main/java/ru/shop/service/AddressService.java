package ru.shop.service;

import ru.shop.domain.AddressDTO;

import java.util.List;

public interface AddressService {
    Long getCountAddress();

    List<AddressDTO> getAllAddress();

    AddressDTO getAddressById(Long categoryId);

    void deleteAddressById(Long categoryId);

    void createNewAddress(AddressDTO categoryDto);

    void updateAddress(AddressDTO categoryDto);
}
