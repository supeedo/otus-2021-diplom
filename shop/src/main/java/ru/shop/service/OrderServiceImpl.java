package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.Order;
import ru.shop.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
