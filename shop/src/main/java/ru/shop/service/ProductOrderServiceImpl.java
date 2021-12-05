package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.OrderItemDTO;
import ru.shop.repository.OrderItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shop.domain.mapper.OrderItemMapper.orderItemMapper;

@Service
public class ProductOrderServiceImpl  implements ProductOrderService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOrderServiceImpl.class.getName());

    private final OrderItemRepository productOrderRepository;

    public ProductOrderServiceImpl(OrderItemRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountProductOrders() {
        return productOrderRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderItemDTO> getAllProductOrders() {
        final var productOrders = productOrderRepository.findAll();
        return productOrders.stream()
                .map(orderItemMapper::orderItemToOrderItemDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public OrderItemDTO getProductOrderById(final Long orderId) {
        LOGGER.debug("Find product order by id = {}", orderId);
        final var productOrder = productOrderRepository
                .findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Product order not found"));
        LOGGER.debug("Found product order by id = {}", productOrder);
        return orderItemMapper.orderItemToOrderItemDTO(productOrder);
    }

    @Transactional
    @Override
    public void deleteProductOrderById(final Long orderId) {
        LOGGER.debug("Delete product order by id = {}", orderId);
        productOrderRepository.deleteById(orderId);
    }

    @Transactional
    @Override
    public void createNewProductOrder(final OrderItemDTO productOrderDto) {
        LOGGER.debug("Create product order by dto = {}", productOrderDto);
        final var productOrder = orderItemMapper.orderItemDtoToOrderItem(productOrderDto);
        productOrderRepository.save(productOrder);
    }

    @Transactional
    @Override
    public void updateProductOrder(final OrderItemDTO productOrderDto) {
        LOGGER.debug("Update product order by dto = {}", productOrderDto);
        final var productOrder = productOrderRepository
                .findById(productOrderDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product order not found"));

    }
}
