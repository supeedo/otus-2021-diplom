package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import ru.shop.domain.Role;
import ru.shop.domain.RoleDTO;
import ru.shop.domain.StatusOrder;
import ru.shop.domain.StatusOrderDTO;

@Mapper(componentModel = "spring")
public interface StatusOrderMapper {

    StatusOrderDTO statusOrderToStatusOrderDto(StatusOrder entity);

    StatusOrder statusOrderDtoToStatusOrder(StatusOrderDTO dto);
}
