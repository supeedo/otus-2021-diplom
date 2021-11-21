package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import ru.shop.domain.UserAddress;
import ru.shop.domain.UserAddressDTO;

@Mapper
public interface UserAddressMapper {

    UserAddressDTO userAddressToUserAddressDto(UserAddress entity);

    UserAddress userAddressDtoToUserAddress(UserAddressDTO dto);
}
