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
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "status", source = "entity.status")
    })
    StatusOrderDTO statusOrderToStatusOrderDto(StatusOrder entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "status", source = "dto.status")
    })
    StatusOrder statusOrderDtoToStatusOrder(StatusOrderDTO dto);
}
