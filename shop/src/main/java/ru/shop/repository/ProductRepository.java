package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
