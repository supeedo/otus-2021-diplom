package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.OrderItem;
import ru.shop.domain.OrderItemDTO;

import static ru.shop.domain.mapper.OrderMapper.orderMapper;
import static ru.shop.domain.mapper.ProductMapper.productMapper;

@Mapper
public interface ProductOrderMapper {

    ProductOrderMapper productOrderMapper = Mappers.getMapper(ProductOrderMapper.class);

    default OrderItemDTO productOrderToProductOrderDTO(OrderItem entity) {
        return new OrderItemDTO(

        );
    }

    default OrderItem productOrderDtoToProductOrder(OrderItemDTO dto) {
        return new OrderItem(
                dto.getId(),
                orderMapper.orderDtoToOrder(dto.getOrder()),
                productMapper.productDtoToProduct(dto.getProduct()),
                dto.getCount()
        );
    }
}
