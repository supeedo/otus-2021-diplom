package ru.shop.service;

import ru.shop.domain.ProductDTO;

import java.util.List;

public interface ProductService {

    Long getCountProduct();

    List<ProductDTO> getAllProduct();

    ProductDTO getProductById(Long productId);

    void deleteProductById(Long productId);

    void createNewProduct(ProductDTO productDto);

    void updateProduct(ProductDTO productDto);

}
