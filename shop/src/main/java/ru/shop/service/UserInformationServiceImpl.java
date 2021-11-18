package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.UserInformationDTO;
import ru.shop.domain.mapper.AddressMapper;
import ru.shop.domain.mapper.UserInformationMapper;
import ru.shop.repository.UserInformationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInformationServiceImpl.class.getName());

    private final UserInformationRepository userInformationRepository;
    private final AddressService addressService;

    public UserInformationServiceImpl(UserInformationRepository userInformationRepository, AddressService addressService) {
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
                .map(UserInformationMapper.INSTANCE::userInformationToUserInformationDto)
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
        return UserInformationMapper.INSTANCE.userInformationToUserInformationDto(userInformation);
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
        final var userInformation = UserInformationMapper.INSTANCE
                .userInformationDtoToUserInformation(userInformationDto);
        userInformationRepository.save(userInformation);
    }
    //TODO доделать проверку на null
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
                AddressMapper.INSTANCE.addressDtoToAddress(
                        addressService.getAddressById(
                                userInformationDto.getAddress().getId()
                        )
                )
        );
    }
}
