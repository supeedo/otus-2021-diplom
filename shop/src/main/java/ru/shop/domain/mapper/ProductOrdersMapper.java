package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.ProductOrders;
import ru.shop.domain.ProductOrdersDTO;

@Mapper(componentModel = "spring")
public interface ProductOrdersMapper {
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    default ProductOrdersDTO productOrdersToProductDto(ProductOrders entity) {
        return new ProductOrdersDTO(
                entity.getId(),
                productMapper.productToProductDto(entity.getProduct()),
                entity.getProductCount()
        );
    }

    default ProductOrders productDtoToProductOrders(ProductOrdersDTO dto) {
        return new ProductOrders(
                dto.getId(),
                productMapper.productDtoToProduct(dto.getProduct()),
                dto.getProductCount()
        );
    }
}
