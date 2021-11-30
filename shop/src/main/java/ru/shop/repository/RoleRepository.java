package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shop.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String name);
}
