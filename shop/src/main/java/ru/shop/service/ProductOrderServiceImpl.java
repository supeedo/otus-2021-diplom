package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.ProductOrderDTO;
import ru.shop.repository.ProductOrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shop.domain.mapper.ProductOrderMapper.productOrderMapper;

@Service
public class ProductOrderServiceImpl  implements ProductOrderService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOrderServiceImpl.class.getName());

    private final ProductOrderRepository productOrderRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountProductOrders() {
        return productOrderRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductOrderDTO> getAllProductOrders() {
        final var productOrders = productOrderRepository.findAll();
        return productOrders.stream()
                .map(productOrderMapper::productOrderToProductOrderDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductOrderDTO getProductOrderById(Long orderId) {
        LOGGER.debug("Find product order by id = {}", orderId);
        final var productOrder = productOrderRepository
                .findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Product order not found"));
        LOGGER.debug("Found product order by id = {}", productOrder);
        return productOrderMapper.productOrderToProductOrderDTO(productOrder);
    }

    @Transactional
    @Override
    public void deleteProductOrderById(Long orderId) {
        LOGGER.debug("Delete product order by id = {}", orderId);
        productOrderRepository.deleteById(orderId);
    }

    @Transactional
    @Override
    public void createNewProductOrder(ProductOrderDTO productOrderDto) {
        LOGGER.debug("Create product order by dto = {}", productOrderDto);
        final var productOrder = productOrderMapper.productOrderDtoToProductOrder(productOrderDto);
        productOrderRepository.save(productOrder);
    }

    @Transactional
    @Override
    public void updateProductOrder(ProductOrderDTO productOrderDto) {
        LOGGER.debug("Update product order by dto = {}", productOrderDto);
        final var productOrder = productOrderRepository
                .findById(productOrderDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product order not found"));

    }
}
