package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.shop.domain.Order;
import ru.shop.domain.OrderDTO;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class, ProductMapper.class, StatusOrderMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "createTime", target = "createTime"),
            @Mapping(source = "deliveryTime", target = "deliveryTime"),
            @Mapping(source = "note", target = "note"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "products", target = "products"),

    })
    OrderDTO orderToOrderDto(Order entity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "createTime", target = "createTime"),
            @Mapping(source = "deliveryTime", target = "deliveryTime"),
            @Mapping(source = "note", target = "note"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "products", target = "products"),

    })
    Order orderDtoToOrder(OrderDTO dto);
}
