package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.UserAddress;
import ru.shop.domain.UserAddressDTO;

@Mapper
public interface UserAddressMapper {

    UserAddressMapper userAddressMapper = Mappers.getMapper(UserAddressMapper.class);

    UserAddressDTO userAddressToUserAddressDto(UserAddress entity);

    UserAddress userAddressDtoToUserAddress(UserAddressDTO dto);
}
