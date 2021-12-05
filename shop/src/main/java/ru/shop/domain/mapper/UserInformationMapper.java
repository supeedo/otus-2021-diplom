package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.UserInformation;
import ru.shop.domain.UserInformationDTO;

@Mapper(componentModel = "spring")
public interface UserInformationMapper {

    UserInformationMapper userInformationMapper = Mappers.getMapper(UserInformationMapper.class);

    UserInformationDTO userInformationToUserInformationDto(UserInformation entity);

    UserInformation userInformationDtoToUserInformation(UserInformationDTO dto);
}
