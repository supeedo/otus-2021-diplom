package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.Order;
import ru.shop.domain.OrderDTO;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class, ProductMapper.class, StatusOrderMapper.class})
public interface OrderMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    StatusOrderMapper statusOrderMapper = Mappers.getMapper(StatusOrderMapper.class);
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

//    @Mappings({
//            @Mapping(target = "id", source = "entity.id"),
//            @Mapping(target = "createTime", source = "entity.createTime"),
//            @Mapping(target = "deliveryTime", source = "entity.deliveryTime"),
//            @Mapping(target = "note", source = "entity.note"),
//            @Mapping(target = "status", source = "entity.status"),
//            @Mapping(target = "user", source = "entity.user"),
//            @Mapping(target = "products", source = "entity.products"),
//
//    })
//    OrderDTO orderToOrderDto(Order entity);

    default OrderDTO orderToOrderDto(Order entity){
        return new OrderDTO(
                entity.getId(),
                entity.getCreateTime(),
                entity.getDeliveryTime(),
                entity.getNote(),
                statusOrderMapper.statusOrderToStatusOrderDto(entity.getStatus()),
                userMapper.userToUserDto(entity.getUser()),
                entity.getProducts().stream().map(productMapper::productToProductDto).collect(Collectors.toList())
        );
    }

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createTime", source = "dto.createTime"),
            @Mapping(target = "deliveryTime", source = "dto.deliveryTime"),
            @Mapping(target = "note", source = "dto.note"),
            @Mapping(target = "status", source = "dto.status"),
            @Mapping(target = "user", source = "dto.user"),
            @Mapping(target = "products", source = "dto.products"),

    })
    Order orderDtoToOrder(OrderDTO dto);
}
