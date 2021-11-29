package ru.shop.service;

import ru.shop.domain.OrderItemDTO;

import java.util.List;

public interface ProductOrderService {

    Long getCountProductOrders();

    List<OrderItemDTO> getAllProductOrders();

    OrderItemDTO getProductOrderById(Long orderId);

    void deleteProductOrderById(Long orderId);

    void createNewProductOrder(OrderItemDTO productOrderDto);

    void updateProductOrder(OrderItemDTO productOrderDto);
}
