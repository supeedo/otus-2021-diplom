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

    default OrderDTO orderToOrderDto(Order entity) {
        return new OrderDTO(
                entity.getId(),
                entity.getNote(),
                statusOrderMapper.statusOrderToStatusOrderDto(entity.getStatus()),
                userMapper.userToUserDto(entity.getUser()),
                entity.getProducts().stream().map(productMapper::productToProductDto).collect(Collectors.toList())
        );
    }

    //    @Mappings({
//            @Mapping(target = "id", source = "dto.id"),
////            @Mapping(target = "note", source = "dto.note"),
////            @Mapping(target = "status", source = "dto.status"),
////            @Mapping(target = "user", source = "dto.user"),
////            @Mapping(target = "products", source = "dto.products"),
////
////    })
////    Order orderDtoToOrder(OrderDTO dto);
    default Order orderDtoToOrder(OrderDTO dto) {
        return new Order(
                dto.getId(),
                dto.getNote(),
                statusOrderMapper.statusOrderDtoToStatusOrder(dto.getStatus()),
                userMapper.userDtoToUser(dto.getUser()),
                dto.getProducts().stream().map(productMapper::productDtoToProduct).collect(Collectors.toList()));
    }
}
