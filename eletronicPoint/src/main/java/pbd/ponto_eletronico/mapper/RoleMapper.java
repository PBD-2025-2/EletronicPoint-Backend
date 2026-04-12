package pbd.ponto_eletronico.mapper;

import pbd.ponto_eletronico.dto.RoleDTO;
import pbd.ponto_eletronico.entity.Role;
import pbd.ponto_eletronico.request.RolePostRequest;
import pbd.ponto_eletronico.request.RolePutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleDTO roleDTO);

    Role toRole(RolePostRequest rolePostRequest);

    Role toRole(RolePutRequest rolePutRequest);

    RoleDTO toRoleDto(Role role);

    List<RoleDTO> toRoleDtos(List<Role> roles);

    List<RoleDTO.Summary> toRoleSummaryDtos(List<Role> roles);
}
