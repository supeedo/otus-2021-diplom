package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import ru.shop.domain.Order;
import ru.shop.domain.OrderDTO;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO orderToOrderDto(Order entity);

    Order orderDtoToOrder(OrderDTO dto);
}
