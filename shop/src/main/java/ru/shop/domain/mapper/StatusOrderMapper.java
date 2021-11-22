package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.shop.domain.Role;
import ru.shop.domain.RoleDTO;
import ru.shop.domain.StatusOrder;
import ru.shop.domain.StatusOrderDTO;

@Mapper(componentModel = "spring")
public interface StatusOrderMapper {



    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "status", target = "status")
    })
    StatusOrderDTO statusOrderToStatusOrderDto(StatusOrder entity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "status", target = "status")
    })
    StatusOrder statusOrderDtoToStatusOrder(StatusOrderDTO dto);
}
