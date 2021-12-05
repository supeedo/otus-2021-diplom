package ru.shop.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.shop.domain.Role;
import ru.shop.domain.RoleDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDto(Role entity);

    Role roleDtoToRole(RoleDTO dto);
}
