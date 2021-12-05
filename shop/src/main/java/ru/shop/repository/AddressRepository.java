package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shop.domain.UserAddress;

public interface AddressRepository extends JpaRepository<UserAddress, Long> {
}
