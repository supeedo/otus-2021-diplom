package ru.shop.service;

import ru.shop.domain.StatusOrderDTO;

import java.util.List;

public interface StatusOrderService {

    Long getCountStatusOrder();

    List<StatusOrderDTO> getAllStatusOrder();

    StatusOrderDTO getStatusOrderById(Long roleId);

    void deleteStatusOrderById(Long roleId);

    void createNewStatusOrder(StatusOrderDTO dto);

    void updateStatusOrder(StatusOrderDTO dto);
}
