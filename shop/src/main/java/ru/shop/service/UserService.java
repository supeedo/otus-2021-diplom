package ru.shop.service;

import ru.shop.domain.UserDTO;

import java.util.List;

public interface UserService {
    Long getCountUser();

    List<UserDTO> getAllUser();

    UserDTO getUserById(Long userId);

    void deleteUserById(Long userId);

    void createNewUser(UserDTO userDto);

    void updateUser(UserDTO userDto);
}
