package ru.shop.service;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.UserDTO;
import ru.shop.domain.mapper.UserMapper;
import ru.shop.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;
    private final UserInformationService userInformationService;
    private final RoleService roleService;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);


    public UserServiceImpl(UserRepository userRepository,
                           @Qualifier("userInformationServiceImpl") UserInformationService userInformationService,
                           @Qualifier("roleServiceImpl") RoleService roleService) {
        this.userRepository = userRepository;
        this.userInformationService = userInformationService;
        this.roleService = roleService;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountUser() {
        return userRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDTO> getAllUser() {
        final var users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUserById(Long userId) {
        LOGGER.debug("Find user by id = {}", userId);
        final var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.userToUserDto(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        LOGGER.debug("Delete user by id = {}", userId);
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public void createNewUser(UserDTO userDto) {
        final var user = userMapper.userDtoToUser(userDto);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(UserDTO userDto) {
        final var user = userRepository
                .findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.isActive());
        userInformationService.updateUserInformation(userDto.getUserInformation());
        roleService.updateRole(userDto.getRole());
    }
}
