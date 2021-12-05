package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.Category;
import ru.shop.domain.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

   CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

   CategoryDTO categoryToCategoryDto(Category entity);

   Category categoryDtoToCategory(CategoryDTO dto);
}
