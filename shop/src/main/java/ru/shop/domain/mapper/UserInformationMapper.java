package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.UserInformation;
import ru.shop.domain.UserInformationDTO;

@Mapper
public interface UserInformationMapper {

    UserInformationMapper INSTANCE = Mappers.getMapper(UserInformationMapper.class);

    UserInformationDTO userInformationToUserInformationDto(UserInformation entity);

    UserInformation userInformationDtoToUserInformation(UserInformationDTO dto);
}
