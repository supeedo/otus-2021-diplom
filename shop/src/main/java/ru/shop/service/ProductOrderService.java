package ru.shop.service;

import ru.shop.domain.ProductOrderDTO;

import java.util.List;

public interface ProductOrderService {

    Long getCountProductOrders();

    List<ProductOrderDTO> getAllProductOrders();

    ProductOrderDTO getProductOrderById(Long orderId);

    void deleteProductOrderById(Long orderId);

    void createNewProductOrder(ProductOrderDTO productOrderDto);

    void updateProductOrder(ProductOrderDTO productOrderDto);
}
