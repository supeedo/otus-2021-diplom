package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.OrderItem;
import ru.shop.domain.OrderItemDTO;

import static ru.shop.domain.mapper.OrderMapper.orderMapper;
import static ru.shop.domain.mapper.ProductMapper.productMapper;

@Mapper
public interface OrderItemMapper {

    OrderItemMapper orderItemMapper = Mappers.getMapper(OrderItemMapper.class);

    default OrderItemDTO orderItemToOrderItemDTO(OrderItem entity) {
        return new OrderItemDTO(
                entity.getId(),
               productMapper.productToProductDto(entity.getProduct()),
                entity.getCount()
        );
    }

    default OrderItem orderItemDtoToOrderItem(OrderItemDTO dto) {
        return new OrderItem(
                dto.getId(),
                productMapper.productDtoToProduct(dto.getProduct()),
                dto.getCount()
        );
    }
}
