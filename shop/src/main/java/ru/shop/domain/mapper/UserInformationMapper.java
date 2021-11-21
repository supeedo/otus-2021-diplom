package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import ru.shop.domain.UserInformation;
import ru.shop.domain.UserInformationDTO;

@Mapper(componentModel = "spring")
public interface UserInformationMapper {

    UserInformationDTO userInformationToUserInformationDto(UserInformation entity);

    UserInformation userInformationDtoToUserInformation(UserInformationDTO dto);
}
