package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import ru.shop.domain.Role;
import ru.shop.domain.RoleDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO roleToRoleDto(Role entity);

    Role roleDtoToRole(RoleDTO dto);
}
