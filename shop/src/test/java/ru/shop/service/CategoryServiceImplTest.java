package ru.shop.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.shop.domain.CategoryDTO;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
@DisplayName("Category service tests")
class CategoryServiceImplTest {
    private final CategoryService service;

    private static final Long COUNT_TEST_CATEGORY = 2L;
    private static final Long BAD_COUNT_TEST_CATEGORY = 3L;
    private static final CategoryDTO FIRST_TEST_CATEGORY = new CategoryDTO(1L, "laptop", "description for laptop category");
    private static final CategoryDTO SECOND_TEST_CATEGORY = new CategoryDTO(2L, "desktop", "description for desktop category");
    private static final CategoryDTO BAD_TEST_CATEGORY = new CategoryDTO(3L, "bad category name", "description for bad category");
    private static final CategoryDTO FIRST_TEST_CATEGORY_FOR_UPDATE = new CategoryDTO(1L, "update laptop", "update description for laptop category");

    CategoryServiceImplTest(@Qualifier("categoryServiceImpl") CategoryService service) {
        this.service = service;
    }

    @Test
    @DisplayName("Getting count categories as expected")
    void getCountServices() {
        final Long actualCountCategory = service.getCountServices();
        assertThat(actualCountCategory)
                .isNotNull()
                .isEqualTo(COUNT_TEST_CATEGORY)
                .isNotEqualTo(BAD_COUNT_TEST_CATEGORY);
    }

    @Test
    @DisplayName("Getting all categories as expected")
    void getAllCategory() {
        final var actualCategories = service.getAllCategory();
        assertThat(actualCategories)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_CATEGORY))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_CATEGORY))
                .containsOnly(FIRST_TEST_CATEGORY, SECOND_TEST_CATEGORY)
                .doesNotContain(BAD_TEST_CATEGORY);
    }

    @Test
    @DisplayName("Getting Category by id as expected")
    void getCategoryById() {
        final var actualCategory = service.getCategoryById(FIRST_TEST_CATEGORY.getId());
        assertThat(actualCategory)
                .isNotNull()
                .isEqualTo(FIRST_TEST_CATEGORY)
                .isNotEqualTo(SECOND_TEST_CATEGORY)
                .isNotEqualTo(BAD_TEST_CATEGORY);
    }

    @Test
    @DisplayName("Getting category by name as expected")
    void getCategoryByName() {
        final var actualCategory = service.getCategoryByName(FIRST_TEST_CATEGORY.getCategoryName());
        assertThat(actualCategory)
                .isNotNull()
                .isEqualTo(FIRST_TEST_CATEGORY)
                .isNotEqualTo(SECOND_TEST_CATEGORY)
                .isNotEqualTo(BAD_TEST_CATEGORY);
    }

    @Test
    @DisplayName("Deleting an category by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteCategoryById() {
        final var actualCategory = service.getCategoryById(FIRST_TEST_CATEGORY.getId());
        assertThat(actualCategory)
                .isNotNull()
                .isEqualTo(FIRST_TEST_CATEGORY)
                .isNotEqualTo(SECOND_TEST_CATEGORY)
                .isNotEqualTo(BAD_TEST_CATEGORY);
        service.deleteCategoryById(FIRST_TEST_CATEGORY.getId());
        final var thrown = catchThrowable(() ->
                service.getCategoryById(FIRST_TEST_CATEGORY.getId())
        );
        assertThat(thrown)
                .isInstanceOf(EntityNotFoundException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Adding a new category happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewCategory() {
        final var actualCategories = service.getAllCategory();
        assertThat(actualCategories)
                .isNotNull()
                .isNotEmpty()
                .hasSize(Math.toIntExact(COUNT_TEST_CATEGORY))
                .hasSizeLessThan(Math.toIntExact(BAD_COUNT_TEST_CATEGORY))
                .containsOnly(FIRST_TEST_CATEGORY, SECOND_TEST_CATEGORY)
                .doesNotContain(BAD_TEST_CATEGORY);
        service.createNewCategory(BAD_TEST_CATEGORY);
        final var actualCategoriesAfterCreate = service.getAllCategory();
        assertThat(actualCategoriesAfterCreate)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThan(Math.toIntExact(COUNT_TEST_CATEGORY))
                .hasSize(Math.toIntExact(BAD_COUNT_TEST_CATEGORY))
                .containsOnly(FIRST_TEST_CATEGORY, SECOND_TEST_CATEGORY, BAD_TEST_CATEGORY);
    }

    @Test
    @DisplayName("Update category as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateCategory() {
        final var actualCategory = service.getCategoryById(FIRST_TEST_CATEGORY.getId());
        assertThat(actualCategory)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_CATEGORY.getId())
                .hasFieldOrPropertyWithValue("categoryName", FIRST_TEST_CATEGORY.getCategoryName())
                .hasFieldOrPropertyWithValue("description", FIRST_TEST_CATEGORY.getDescription());
        service.updateCategory(FIRST_TEST_CATEGORY_FOR_UPDATE);
        final var actualCategoryAfterUpdate = service.getCategoryById(FIRST_TEST_CATEGORY.getId());
        assertThat(actualCategoryAfterUpdate)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", FIRST_TEST_CATEGORY_FOR_UPDATE.getId())
                .hasFieldOrPropertyWithValue("categoryName", FIRST_TEST_CATEGORY_FOR_UPDATE.getCategoryName())
                .hasFieldOrPropertyWithValue("description", FIRST_TEST_CATEGORY_FOR_UPDATE.getDescription());
    }
}