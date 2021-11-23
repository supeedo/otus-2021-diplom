package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import ru.shop.domain.CategoryDTO;
import ru.shop.domain.ProductDTO;
import ru.shop.domain.RoleDTO;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
@DisplayName("Product service tests")
class ProductServiceImplTest {

    private final ProductService service;

    private static final Long COUNT_TEST_PRODUCT = 2L;
    private static final Long BAD_COUNT_TEST_PRODUCT = 3L;
    private static final ProductDTO FIRST_TEST_PRODUCT =
            new ProductDTO(1L,
                    new CategoryDTO(1L, "laptop", "description for laptop category"),
                    "Lenovo", "lenovo laptop", 120.0, true);
    private static final ProductDTO SECOND_TEST_PRODUCT =
            new ProductDTO(2L,
                    new CategoryDTO(1L, "laptop", "description for laptop category"),
                    "Macbook pro", "Apple laptop 16\"", 250.0, true);
    private static final ProductDTO BAD_TEST_PRODUCT =
            new ProductDTO(3L,
                    new CategoryDTO(2L, "desktop", "description for desktop category"),
                    "bad product", "bad description", 999.99, false);
    private static final ProductDTO FIRST_TEST_PRODUCT_FOR_UPDATE =
            new ProductDTO(1L,
                    new CategoryDTO(2L, "desktop", "description for desktop category"),
                    "update product", "update description", 12345.12, false);

    ProductServiceImplTest(@Qualifier("productServiceImpl") ProductService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count products as expected")
    void getCountProduct() {
        final Long actualCountProducts = service.getCountProduct();
        assertThat(actualCountProducts)
                .isNotNull()
                .isEqualTo(COUNT_TEST_PRODUCT)
                .isNotEqualTo(BAD_COUNT_TEST_PRODUCT);
    }

    @Test
    @DisplayName("Getting all products as expected")
    void getAllProduct() {
        final var actualProducts = service.getAllProduct();
        assertThat(actualProducts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_PRODUCT))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_PRODUCT))
                .containsOnly(FIRST_TEST_PRODUCT, SECOND_TEST_PRODUCT)
                .doesNotContain(BAD_TEST_PRODUCT);
    }

    @Test
    @DisplayName("Getting product by id as expected")
    void getProductById() {
        final var actualProduct = service.getProductById(FIRST_TEST_PRODUCT.getId());
        assertThat(actualProduct)
                .isNotNull()
                .isEqualTo(FIRST_TEST_PRODUCT)
                .isNotEqualTo(SECOND_TEST_PRODUCT)
                .isNotEqualTo(BAD_TEST_PRODUCT);
    }

    @Test
    @DisplayName("Deleting an product by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteProductById() {
        final var actualProduct = service.getProductById(SECOND_TEST_PRODUCT.getId());
        assertThat(actualProduct)
                .isNotNull()
                .isEqualTo(SECOND_TEST_PRODUCT)
                .isNotEqualTo(FIRST_TEST_PRODUCT)
                .isNotEqualTo(BAD_TEST_PRODUCT);
        service.deleteProductById(SECOND_TEST_PRODUCT.getId());
        final var thrown = catchThrowable(() ->
                service.getProductById(SECOND_TEST_PRODUCT.getId()));
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }


    @Test
    @DisplayName("Deleting an product by id if role by use ")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteProductByIdIfUsed() {
//                final var thrownForReferencesKey = catchThrowable(() ->
//                service.deleteProductById(FIRST_TEST_PRODUCT.getId())
//        );
//        assertThat(thrownForReferencesKey)
//                .isInstanceOf(DataIntegrityViolationException.class)
//                .isNotInstanceOf(EntityNotFoundException.class)
//                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new product happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewProduct() {
        final var actualProducts = service.getAllProduct();
        assertThat(actualProducts)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_PRODUCT))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_PRODUCT))
                .containsOnly(FIRST_TEST_PRODUCT, SECOND_TEST_PRODUCT)
                .doesNotContain(BAD_TEST_PRODUCT);
        service.createNewProduct(BAD_TEST_PRODUCT);
        final var actualProductsAfterCreate = service.getAllProduct();
        assertThat(actualProductsAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_PRODUCT))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_PRODUCT))
                .containsOnly(FIRST_TEST_PRODUCT, SECOND_TEST_PRODUCT, BAD_TEST_PRODUCT);
    }

    @Test
    @DisplayName("Update product as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateProduct() {
        final var actualProduct = service.getProductById(FIRST_TEST_PRODUCT.getId());
        assertThat(actualProduct)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_PRODUCT.getId())
                .hasFieldOrPropertyWithValue("category", FIRST_TEST_PRODUCT.getCategory())
                .hasFieldOrPropertyWithValue("name", FIRST_TEST_PRODUCT.getName())
                .hasFieldOrPropertyWithValue("description", FIRST_TEST_PRODUCT.getDescription())
                .hasFieldOrPropertyWithValue("price", FIRST_TEST_PRODUCT.getPrice())
                .hasFieldOrPropertyWithValue("active", FIRST_TEST_PRODUCT.isActive());
        service.updateProduct(FIRST_TEST_PRODUCT_FOR_UPDATE);
        final var actualProductAfterUpdate = service.getProductById(FIRST_TEST_PRODUCT.getId());
        assertThat(actualProductAfterUpdate)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_PRODUCT_FOR_UPDATE.getId())
                .hasFieldOrPropertyWithValue("category", FIRST_TEST_PRODUCT_FOR_UPDATE.getCategory())
                .hasFieldOrPropertyWithValue("name", FIRST_TEST_PRODUCT_FOR_UPDATE.getName())
                .hasFieldOrPropertyWithValue("description", FIRST_TEST_PRODUCT_FOR_UPDATE.getDescription())
                .hasFieldOrPropertyWithValue("price", FIRST_TEST_PRODUCT_FOR_UPDATE.getPrice())
                .hasFieldOrPropertyWithValue("active", FIRST_TEST_PRODUCT_FOR_UPDATE.isActive());
    }
}