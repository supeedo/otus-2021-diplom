package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.UserInformationDTO;
import ru.shop.repository.UserInformationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shop.domain.mapper.UserAddressMapper.userAddressMapper;
import static ru.shop.domain.mapper.UserInformationMapper.userInformationMapper;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInformationServiceImpl.class.getName());

    private final UserInformationRepository userInformationRepository;
    private final UserAddressService addressService;

    public UserInformationServiceImpl(UserInformationRepository userInformationRepository, UserAddressService addressService) {
        this.userInformationRepository = userInformationRepository;
        this.addressService = addressService;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountUserInformation() {
        return userInformationRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInformationDTO> getAllUserInformation() {
        final var userInformations = userInformationRepository.findAll();
        return userInformations.stream()
                .map(userInformationMapper::userInformationToUserInformationDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserInformationDTO getUserInformationById(Long userInformationId) {
        LOGGER.debug("Find userInformation by id = {}", userInformationId);
        final var userInformation = userInformationRepository
                .findById(userInformationId)
                .orElseThrow(() -> new EntityNotFoundException("UserInformation not found"));
        LOGGER.debug("Found userInformation by id = {}", userInformation);
        return userInformationMapper.userInformationToUserInformationDto(userInformation);
    }

    @Transactional
    @Override
    public void deleteUserInformationById(Long userInformationId) {
        LOGGER.debug("Delete userInformation by id = {}", userInformationId);
        userInformationRepository.deleteById(userInformationId);
    }

    @Transactional
    @Override
    public void createNewUserInformation(UserInformationDTO userInformationDto) {
        LOGGER.debug("Create userInformation by dto = {}", userInformationDto);
        final var userInformation = userInformationMapper
                .userInformationDtoToUserInformation(userInformationDto);
        userInformationRepository.save(userInformation);
    }

    @Transactional
    @Override
    public void updateUserInformation(UserInformationDTO userInformationDto) {
        LOGGER.debug("Update userInformation by dto = {}", userInformationDto);
        final var userInformation = userInformationRepository
                .findById(userInformationDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("UserInformation not found"));
        userInformation.setFirstName(userInformationDto.getFirstName());
        userInformation.setLastName(userInformationDto.getLastName());
        userInformation.setPatronymic(userInformationDto.getPatronymic());
        userInformation.setPhone(userInformationDto.getPhone());
        addressService.updateAddress(userInformationDto.getAddress());
        userInformation.setAddress(
                userAddressMapper.userAddressDtoToUserAddress(
                        addressService.getAddressById(
                                userInformationDto.getAddress().getId()
                        )
                )
        );
    }
}
