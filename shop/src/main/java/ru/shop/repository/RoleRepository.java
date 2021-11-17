package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shop.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
