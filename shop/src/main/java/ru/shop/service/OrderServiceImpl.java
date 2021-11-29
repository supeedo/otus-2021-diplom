package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.OrderDTO;
import ru.shop.repository.OrderItemRepository;
import ru.shop.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shop.domain.mapper.OrderItemMapper.orderItemMapper;
import static ru.shop.domain.mapper.OrderMapper.orderMapper;
import static ru.shop.domain.mapper.StatusOrderMapper.statusOrderMapper;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountOrder() {
        return orderRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> getAllOrder() {
        final var orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDTO getOrderById(Long orderId) {
        LOGGER.debug("Find order by id = {}", orderId);
        final var order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        LOGGER.debug("Found order by id = {}", order);
        return orderMapper.orderToOrderDTO(order);
    }

    @Transactional
    @Override
    public void deleteOrderById(Long orderId) {
        LOGGER.debug("Delete order by id = {}", orderId);
        orderRepository.deleteById(orderId);
    }

    @Transactional
    @Override
    public void createNewOrder(OrderDTO orderDto) {
        LOGGER.debug("Create order by dto = {}", orderDto);
        final var orderItems = orderDto.getOrdersItems().stream().map(orderItemMapper::orderItemDtoToOrderItem).collect(Collectors.toList());
        final var orderSavedItem = orderItems.stream().map(orderItemRepository::save).collect(Collectors.toList());
        final var order = orderMapper.orderDtoToOrderForSave(orderDto);
        order.setOrderItems(orderSavedItem);
        final var savedOrder = orderRepository.save(order);
        orderSavedItem.forEach(orderItem->orderItem.setOrder(savedOrder));
    }

    @Transactional
    @Override
    public void updateOrder(OrderDTO orderDto) {
        LOGGER.debug("Update order by dto = {}", orderDto);
        final var order = orderRepository
                .findById(orderDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setNote(orderDto.getNote());
        order.setStatus(statusOrderMapper.statusOrderDtoToStatusOrder(orderDto.getStatus()));
        final var orderItems = orderDto
                .getOrdersItems()
                .stream()
                .map(orderItemMapper::orderItemDtoToOrderItem)
                .collect(Collectors.toList());
        order.setOrderItems(orderItems);
    }
}
