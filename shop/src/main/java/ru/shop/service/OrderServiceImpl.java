package ru.shop.service;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.OrderDTO;
import ru.shop.domain.mapper.*;
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
    private final ProductOrdersMapper productOrderMapper = Mappers.getMapper(ProductOrdersMapper.class);
    private final StatusOrderMapper statusOrderMapper = Mappers.getMapper(StatusOrderMapper.class);

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
                .map(orderMapper::orderToOrderDto)
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
        return orderMapper.orderToOrderDto(order);
    }

    @Transactional
    @Override
    public void deleteOrderById(Long orderId) {
        LOGGER.debug("Delete order by id = {}", orderId);
        orderRepository.deleteById(orderId);
    }

    @Transactional
    @Override
    public void createNewOrder(OrderDTO dto) {
        LOGGER.debug("Create order by dto = {}", dto);
        final var order = orderMapper.orderDtoToOrder(dto);
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void updateOrder(OrderDTO dto) {
        LOGGER.debug("Update order by dto = {}", dto);

        final var order = orderRepository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setNote(dto.getNote());
        order.setStatus(statusOrderMapper.statusOrderDtoToStatusOrder(dto.getStatus()));
        order.setUser(userMapper.userDtoToUser(dto.getUser()));
        order.setProducts(dto.getProducts()
                .stream()
                .map(productOrderMapper::productDtoToProductOrders)
                .collect(Collectors.toList()));
    }
}
