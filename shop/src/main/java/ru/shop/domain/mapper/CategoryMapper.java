package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import ru.shop.domain.Category;
import ru.shop.domain.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

   CategoryDTO categoryToCategoryDto(Category entity);

   Category categoryDtoToCategory(CategoryDTO dto);
}
