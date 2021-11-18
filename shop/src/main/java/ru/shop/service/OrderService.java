package ru.shop.service;

import ru.shop.domain.OrderDTO;

import java.util.List;

public interface OrderService {
    Long getCountOrder();

    List<OrderDTO> getAllOrder();

    OrderDTO getOrderById(Long orderId);

    void deleteOrderById(Long orderId);

    void createNewOrder(OrderDTO orderDto);

    void updateOrder(OrderDTO orderDto);
}
