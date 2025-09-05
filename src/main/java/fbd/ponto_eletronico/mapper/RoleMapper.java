package fbd.ponto_eletronico.mapper;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.dto.RoleDTO;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.request.EmployeePostRequest;
import fbd.ponto_eletronico.request.EmployeePutRequest;
import fbd.ponto_eletronico.request.RolePostRequest;
import fbd.ponto_eletronico.request.RolePutRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleDTO roleDTO);

    Role toRole(RolePostRequest rolePostRequest);

    Role toRole(RolePutRequest rolePutRequest);

    RoleDTO toRoleDto(Role role);

    List<RoleDTO> toRoleDtos(List<Role> roles);
}
