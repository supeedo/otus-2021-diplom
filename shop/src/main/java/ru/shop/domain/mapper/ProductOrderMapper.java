package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.ProductOrder;
import ru.shop.domain.ProductOrderDTO;

@Mapper
public interface ProductOrderMapper {
    OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    default ProductOrderDTO productOrderToProductOrderDTO(ProductOrder entity) {
        return new ProductOrderDTO(

        );
    }

    default ProductOrder productOrderDtoToProductOrder(ProductOrderDTO dto) {
        return new ProductOrder(
                dto.getId(),
                orderMapper.orderDtoToOrder(dto.getOrder()),
                productMapper.productDtoToProduct(dto.getProduct()),
                dto.getCount()
        );
    }
}
