package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.AddressDTO;
import ru.shop.domain.mapper.AddressMapper;
import ru.shop.domain.mapper.RoleMapper;
import ru.shop.repository.AddressRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class.getName());

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountAddress() {
        return addressRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> getAllAddress() {
        final var addresses = addressRepository.findAll();
        return addresses.stream()
                .map(AddressMapper.INSTANCE::addressToAddressDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public AddressDTO getAddressById(Long addressId) {
        LOGGER.debug("Find address by id = {}", addressId);
        final var address = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
        LOGGER.debug("Found address by id = {}", address);
        return AddressMapper.INSTANCE.addressToAddressDto(address);
    }

    @Transactional
    @Override
    public void deleteAddressById(Long addressId) {
        LOGGER.debug("Delete address by id = {}", addressId);
        addressRepository.deleteById(addressId);
    }

    @Transactional
    @Override
    public void createNewAddress(AddressDTO addressDto) {
        LOGGER.debug("Create address by dto = {}", addressDto);
        final var address = AddressMapper.INSTANCE.addressDtoToAddress(addressDto);
        addressRepository.save(address);
    }

    @Transactional
    @Override
    public void updateAddress(AddressDTO addressDto) {
        LOGGER.debug("Update address by dto = {}", addressDto);
        final var address = addressRepository
                .findById(addressDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setFloor(addressDto.getFloor());
        address.setApartmentNumber(addressDto.getApartmentNumber());
    }
}
