package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.User;
import ru.shop.domain.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDto(User entity);

    User userDtoToUser(UserDTO dto);
}
