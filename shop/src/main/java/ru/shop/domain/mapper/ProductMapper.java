package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.Product;
import ru.shop.domain.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDto(Product entity);

    Product productDtoToProduct(ProductDTO dto);
}
