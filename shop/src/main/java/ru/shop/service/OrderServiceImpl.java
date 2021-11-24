package ru.shop.service;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.Order;
import ru.shop.domain.OrderDTO;
import ru.shop.domain.mapper.OrderMapper;
import ru.shop.domain.mapper.StatusOrderMapper;
import ru.shop.domain.mapper.UserMapper;
import ru.shop.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class.getName());

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final StatusOrderMapper statusOrderMapper = Mappers.getMapper(StatusOrderMapper.class);

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<Order> getAll() {
        return orderRepository.findAll();
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
        final var order = orderMapper.orderDtoToOrder(orderDto);
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void updateOrder(OrderDTO orderDto) {
        LOGGER.debug("Update order by dto = {}", orderDto);
        final var order = orderRepository
                .findById(orderDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setUser(userMapper.userDtoToUser(orderDto.getUser()));
        order.setNote(orderDto.getNote());
        order.setStatus(statusOrderMapper.statusOrderDtoToStatusOrder(orderDto.getStatus()));
    }
}
