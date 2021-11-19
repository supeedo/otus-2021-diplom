package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.OrderDTO;
import ru.shop.domain.mapper.OrderMapper;
import ru.shop.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

//TODO Таблица в БД еще не создана
//@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class.getName());

//    private final OrderRepository orderRepository;
//
//    public OrderServiceImpl(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public Long getCountOrder() {
//        return orderRepository.count();
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<OrderDTO> getAllOrder() {
//        final var orders = orderRepository.findAll();
//        return orders.stream()
//                .map(OrderMapper.INSTANCE::orderToOrderDto)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public OrderDTO getOrderById(Long orderId) {
//        LOGGER.debug("Find order by id = {}", orderId);
//        final var order = orderRepository
//                .findById(orderId)
//                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
//        LOGGER.debug("Found order by id = {}", order);
//        return OrderMapper.INSTANCE.orderToOrderDto(order);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public void deleteOrderById(Long orderId) {
//        LOGGER.debug("Delete order by id = {}", orderId);
//        orderRepository.deleteById(orderId);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public void createNewOrder(OrderDTO orderDto) {
//        LOGGER.debug("Create order by dto = {}", orderDto);
//        final var order = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
//        orderRepository.save(order);
//    }
//    //TODO доделать проверку на null
//    @Transactional(readOnly = true)
//    @Override
//    public void updateOrder(OrderDTO orderDto) {
//        LOGGER.debug("Update order by dto = {}", orderDto);
//        //TODO сформировать ДТО
////        final var order = orderRepository
////                .findById(orderDto.getId())
////                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
//    }
}
