package ru.shop.service;

import ru.shop.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {

    Long getCountServices();

    List<CategoryDTO> getAllCategory();

    CategoryDTO getCategoryById(Long categoryId);

    void deleteCategoryById(Long categoryId);

    CategoryDTO getCategoryByName(String categoryName);

    void createNewCategory(CategoryDTO categoryDto);

    void updateCategory(CategoryDTO categoryDto);

}
