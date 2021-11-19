package ru.shop.service;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.domain.RoleDTO;
import ru.shop.domain.mapper.RoleMapper;
import ru.shop.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class.getName());

    private final RoleRepository roleRepository;

   private final RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCountRole() {
        return roleRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleDTO> getAllRole() {
        final var roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::roleToRoleDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public RoleDTO getRoleById(Long roleId) {
        LOGGER.debug("Find role by id = {}", roleId);
        final var role = roleRepository
                .findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        LOGGER.debug("Found role by id = {}", role);
        return roleMapper.roleToRoleDto(role);
    }

    @Transactional
    @Override
    public void deleteRoleById(Long roleId) {
        LOGGER.debug("Delete role by id = {}", roleId);
        roleRepository.deleteById(roleId);
    }

    @Transactional
    @Override
    public void createNewRole(RoleDTO roleDto) {
        LOGGER.debug("Create role by dto = {}", roleDto);
        final var role = roleMapper.roleDtoToRole(roleDto);
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void updateRole(RoleDTO roleDto) {
        LOGGER.debug("Update role by dto = {}", roleDto);
        final var role = roleRepository
                .findById(roleDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        role.setRoleName(roleDto.getRoleName());
    }
}
