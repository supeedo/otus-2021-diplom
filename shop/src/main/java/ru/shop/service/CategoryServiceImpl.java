package ru.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.Category;
import ru.shop.domain.CategoryDTO;
import ru.shop.domain.mapper.CategoryMapper;
import ru.shop.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class.getName());

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountServices() {
        return categoryRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAllCategory() {
        final var categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO getCategoryById(final Long categoryId) {
        LOGGER.debug("Find category by id = {}", categoryId);
        final var category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        LOGGER.debug("Found category by id = {}", category);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO getCategoryByName(final String categoryName) {
        LOGGER.debug("Find category by name = {}", categoryName);
        final var category = categoryRepository.findCategoryByCategoryName(categoryName);
        LOGGER.debug("Found category by name = {}", category);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }

    @Transactional
    @Override
    public void deleteCategoryById(final Long categoryId) {
        LOGGER.debug("Delete category by id = {}", categoryId);
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    @Override
    public void createNewCategory(final CategoryDTO categoryDto) {
        LOGGER.debug("Create category by dto = {}", categoryDto);
        final var category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void updateCategory(final CategoryDTO categoryDto) {
        LOGGER.debug("Update category by dto = {}", categoryDto);
        final var category = categoryRepository
                .findById(categoryDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
    }

}
