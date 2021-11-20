package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import ru.shop.domain.Product;
import ru.shop.domain.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToProductDto(Product entity);

    Product productDtoToProduct(ProductDTO dto);
}
