package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shop.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
