package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.Order;
import ru.shop.domain.OrderDTO;

import java.util.stream.Collectors;

import static ru.shop.domain.mapper.OrderItemMapper.orderItemMapper;
import static ru.shop.domain.mapper.StatusOrderMapper.statusOrderMapper;
import static ru.shop.domain.mapper.UserMapper.userMapper;

@Mapper
public interface OrderMapper {
    OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    default OrderDTO orderToOrderDTO(Order entity) {
        return new OrderDTO(
                entity.getId(),
                userMapper.userToUserDto(entity.getUser()),
                entity.getNote(),
                statusOrderMapper.statusOrderToStatusOrderDto(entity.getStatus()),
                entity.getOrderItems().stream()
                        .map(orderItemMapper::orderItemToOrderItemDTO)
                        .collect(Collectors.toList())
        );
    }

    default Order orderDtoToOrder(OrderDTO dto) {
        return new Order(
                dto.getId(),
                userMapper.userDtoToUser(dto.getUser()),
                dto.getNote(),
                statusOrderMapper.statusOrderDtoToStatusOrder(dto.getStatus()),
                dto.getOrdersItems().stream()
                        .map(orderItemMapper::orderItemDtoToOrderItem)
                        .collect(Collectors.toList())
        );
    }

    default Order orderDtoToOrderForSave(OrderDTO dto) {
        return new Order(
                dto.getId(),
                userMapper.userDtoToUser(dto.getUser()),
                dto.getNote(),
                statusOrderMapper.statusOrderDtoToStatusOrder(dto.getStatus())
        );
    }

}
