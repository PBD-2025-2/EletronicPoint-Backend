package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.RoleDTO;
import fbd.ponto_eletronico.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toRoleDto(Role role);

    List<RoleDTO> toRoleDtos(List<Role> roles);
}
