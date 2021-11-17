package ru.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.domain.UserInformation;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
}
