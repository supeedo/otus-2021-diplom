package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.UserDTO;
import ru.shop.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shop.domain.mapper.RoleMapper.roleMapper;
import static ru.shop.domain.mapper.UserMapper.userMapper;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;
    private final UserInformationService userInformationService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           @Qualifier("userInformationServiceImpl") UserInformationService userInformationService,
                           @Qualifier("roleServiceImpl") RoleService roleService,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userInformationService = userInformationService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public boolean createUser(final UserDTO userDTO) {
        final String email = userDTO.getEmail();
        final var actualUser = userRepository.findUserByEmail(email);
        if (actualUser != null) {
            throw new RuntimeException("User with this email is already registered");
        } else {
            final var createdUser = userMapper.userDtoToUser(userDTO);
            createdUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            createdUser.setActive(true);
            createdUser.setRole(roleMapper.roleDtoToRole(roleService.getRoleByRoleName("ROLE_USER")));
            userRepository.save(createdUser);
            LOGGER.info("Saving new User with email: {}", email);
            return true;
        }

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
