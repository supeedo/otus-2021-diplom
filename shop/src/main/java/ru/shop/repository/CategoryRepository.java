package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByCategoryName(String categoryName);
}
