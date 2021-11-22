package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.Order;
import ru.shop.domain.OrderDTO;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class, ProductMapper.class, StatusOrderMapper.class})
public interface OrderMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    StatusOrderMapper statusOrderMapper = Mappers.getMapper(StatusOrderMapper.class);
    ProductOrdersMapper productOrderMapper = Mappers.getMapper(ProductOrdersMapper.class);

    default OrderDTO orderToOrderDto(Order entity) {
        return new OrderDTO(
                entity.getId(),
                entity.getNote(),
                statusOrderMapper.statusOrderToStatusOrderDto(entity.getStatus()),
                userMapper.userToUserDto(entity.getUser()),
                entity.getProducts().stream().map(productOrderMapper::productOrdersToProductDto).collect(Collectors.toList())
        );
    }

    default Order orderDtoToOrder(OrderDTO dto) {
        return new Order(
                dto.getId(),
                dto.getNote(),
                statusOrderMapper.statusOrderDtoToStatusOrder(dto.getStatus()),
                userMapper.userDtoToUser(dto.getUser()),
                dto.getProducts().stream().map(productOrderMapper::productDtoToProductOrders).collect(Collectors.toList()));
    }
}
