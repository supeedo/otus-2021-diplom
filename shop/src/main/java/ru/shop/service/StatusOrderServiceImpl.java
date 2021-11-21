package ru.shop.service;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.RoleDTO;
import ru.shop.domain.StatusOrderDTO;
import ru.shop.domain.mapper.StatusOrderMapper;
import ru.shop.repository.StatusOrderRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusOrderServiceImpl implements StatusOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusOrderServiceImpl.class.getName());

    private final StatusOrderRepository statusOrderRepository;

   private final StatusOrderMapper statusOrderMapper = Mappers.getMapper(StatusOrderMapper.class);

    public StatusOrderServiceImpl(StatusOrderRepository statusOrderRepository) {
        this.statusOrderRepository = statusOrderRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountStatusOrder() {
        return statusOrderRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StatusOrderDTO> getAllStatusOrder() {
        final var statuses = statusOrderRepository.findAll();
        return statuses.stream()
                .map(statusOrderMapper::statusOrderToStatusOrderDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public StatusOrderDTO getStatusOrderById(Long roleId) {
        LOGGER.debug("Find status by id = {}", roleId);
        final var status = statusOrderRepository
                .findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));
        LOGGER.debug("Found status by id = {}", status);
        return statusOrderMapper.statusOrderToStatusOrderDto(status);
    }

    @Transactional
    @Override
    public void deleteStatusOrderById(Long roleId) {
        LOGGER.debug("Delete status by id = {}", roleId);
        statusOrderRepository.deleteById(roleId);
    }

    @Transactional
    @Override
    public void createNewStatusOrder(StatusOrderDTO dto) {
        LOGGER.debug("Create status by dto = {}", dto);
        final var status = statusOrderMapper.statusOrderDtoToStatusOrder(dto);
        statusOrderRepository.save(status);
    }

    @Transactional
    @Override
    public void updateStatusOrder(StatusOrderDTO dto) {
        LOGGER.debug("Update status by dto = {}", dto);
        final var role = statusOrderRepository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));
        role.setStatus(dto.getStatus());
    }
}
