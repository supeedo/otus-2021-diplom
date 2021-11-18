package ru.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceImplTest {

    private final CategoryService service;

    CategoryServiceImplTest(@Qualifier("categoryServiceImpl") CategoryService service) {
        this.service = service;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCountServices() {
        System.out.println(
        service.getCountServices()
        );
    }

    @Test
    void getAllCategory() {
    }

    @Test
    void getCategoryById() {
    }

    @Test
    void getCategoryByName() {
    }

    @Test
    void deleteCategoryById() {
    }

    @Test
    void createNewCategory() {
    }

    @Test
    void updateCategory() {
    }
}