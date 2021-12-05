package ru.shop.service;

import ru.shop.domain.RoleDTO;

import java.util.List;

public interface RoleService {



    Long getCountRole();

    List<RoleDTO> getAllRole();

    RoleDTO getRoleByRoleName(String name);

    RoleDTO getRoleById(Long roleId);

    void deleteRoleById(Long roleId);

    void createNewRole(RoleDTO roleDto);

    void updateRole(RoleDTO roleDto);
}
