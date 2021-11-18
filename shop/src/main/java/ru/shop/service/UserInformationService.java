package ru.shop.service;

import ru.shop.domain.UserInformationDTO;

import java.util.List;

public interface UserInformationService {

    Long getCountUserInformation();

    List<UserInformationDTO> getAllUserInformation();

    UserInformationDTO getUserInformationById(Long userInformationId);

    void deleteUserInformationById(Long userInformationId);

    void createNewUserInformation(UserInformationDTO categoryDto);

    void updateUserInformation(UserInformationDTO categoryDto);

}
