package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.ProductDTO;
import ru.shop.domain.mapper.CategoryMapper;
import ru.shop.domain.mapper.ProductMapper;
import ru.shop.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class.getName());

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountProduct() {
        return productRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getAllProduct() {
        final var products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getProductById(Long productId) {
        LOGGER.debug("Find product by id = {}", productId);
        final var product = productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        LOGGER.debug("Found product by id = {}", product);
        return ProductMapper.INSTANCE.productToProductDto(product);
    }

    @Transactional
    @Override
    public void deleteProductById(Long productId) {
        LOGGER.debug("Delete product by id = {}", productId);
        productRepository.deleteById(productId);
    }

    @Transactional
    @Override
    public void createNewProduct(ProductDTO productDto) {
        LOGGER.debug("Create product by dto = {}", productDto);
        final var product = ProductMapper.INSTANCE.productDtoToProduct(productDto);
        productRepository.save(product);
    }
    //TODO доделать проверку на null
    @Transactional
    @Override
    public void updateProduct(ProductDTO productDto) {
        LOGGER.debug("Update product by dto = {}", productDto);
        final var product = productRepository
                .findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setActive(productDto.isActive());
        categoryService.updateCategory(productDto.getCategory());
        product.setCategory(
                CategoryMapper.INSTANCE.categoryDtoToCategory(
                        categoryService.getCategoryById(
                                productDto.getCategory().getId()
                        )
                )
        );
    }
}
