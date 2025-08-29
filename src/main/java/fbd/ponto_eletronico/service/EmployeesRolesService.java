package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.mapper.RolesEmployeesMapper;
import fbd.ponto_eletronico.repository.EmployeesRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesRolesService {
    private final EmployeesRolesRepository employeesRolesRepository;
    private final RolesEmployeesMapper rolesEmployeesMapper;

    public List<EmployeesRolesDTO> listAll() {
        List<EmployeesRoles> employeesRoles = employeesRolesRepository.findAll();
        return rolesEmployeesMapper.employeesRolesDtos(employeesRoles);
    }
}
