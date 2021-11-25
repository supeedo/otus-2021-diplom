package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.ProductOrder;
import ru.shop.domain.ProductOrderDTO;

import static ru.shop.domain.mapper.OrderMapper.orderMapper;
import static ru.shop.domain.mapper.ProductMapper.productMapper;

@Mapper
public interface ProductOrderMapper {

    ProductOrderMapper productOrderMapper = Mappers.getMapper(ProductOrderMapper.class);

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
