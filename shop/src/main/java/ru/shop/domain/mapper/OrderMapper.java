package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.Order;
import ru.shop.domain.OrderDTO;

import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    StatusOrderMapper statusOrderMapper = Mappers.getMapper(StatusOrderMapper.class);
    ProductOrderMapper productOrderMapper = Mappers.getMapper(ProductOrderMapper.class);

    default OrderDTO orderToOrderDTO(Order entity) {
        return new OrderDTO(
                entity.getId(),
                userMapper.userToUserDto(entity.getUser()),
                entity.getNote(),
                statusOrderMapper.statusOrderToStatusOrderDto(entity.getStatus()),
                entity.getProductOrders().stream()
                        .map(productOrderMapper::productOrderToProductOrderDTO)
                        .collect(Collectors.toList())
        );
    }

    default Order orderDtoToOrder(OrderDTO dto) {
        return new Order(
                dto.getId(),
                userMapper.userDtoToUser(dto.getUser()),
                dto.getNote(),
                statusOrderMapper.statusOrderDtoToStatusOrder(dto.getStatus()),
                dto.getProductOrders().stream()
                        .map(productOrderMapper::productOrderDtoToProductOrder)
                        .collect(Collectors.toList())
        );
    }

}
