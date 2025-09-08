package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.mapper.EmployeeMapper;
import fbd.ponto_eletronico.mapper.RoleMapper;
import fbd.ponto_eletronico.mapper.RolesEmployeesMapper;
import fbd.ponto_eletronico.repository.EmployeesRolesRepository;
import fbd.ponto_eletronico.repository.RoleRepository;
import fbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeesRolesService {
    private final EmployeesRolesRepository employeesRolesRepository;
    private final RolesEmployeesMapper rolesEmployeesMapper;
    private final CompanyMapper companyMapper;
    private final CompanyService companyService;
    private final RoleMapper roleMapper;
    private final RoleService roleService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;
    private final RoleRepository roleRepository;

    public List<EmployeesRolesDTO> listAll() {
        List<EmployeesRoles> employeesRoles = employeesRolesRepository.findAll();
        return rolesEmployeesMapper.employeesRolesDtos(employeesRoles);
    }

    public EmployeesRolesDTO findById(Long id){
        Optional<EmployeesRoles> employeesRolesData = employeesRolesRepository.findById(id);
        return rolesEmployeesMapper.employeeRolesDto(employeesRolesData.
                orElseThrow(() -> new BadRequestException("Id Not Found")));
    }

    public List<EmployeesRolesDTO> findByEmployee(String cpf){
        List<Employee> employeesData = employeeMapper.toEmployees(employeeService.findByCpf(cpf));
        List<EmployeesRoles> employeesRolesData = employeesRolesRepository.findByEmployee(employeesData.getFirst());
        return rolesEmployeesMapper.employeesRolesDtos(employeesRolesData);
    }

    public List<EmployeesRolesDTO> findByEmployeeRole(String cpf, String roleName){
        List<EmployeesRoles> employeesRolesData = rolesEmployeesMapper.toEmployeesRoles(findByEmployee(cpf));
        List<EmployeesRoles> filterRolesName = employeesRolesData.stream()
                        .filter(employeesRoles -> employeesRoles.
                                getRole().getName().equalsIgnoreCase(roleName)).toList();

        return rolesEmployeesMapper.employeesRolesDtos(filterRolesName);

    }

    @Transactional
    public EmployeesRoles save(EmployeesRolesPostRequest employeesRolesPostRequest){
        Role roleData = roleMapper.toRole(roleService.findById(employeesRolesPostRequest.roleId()));
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeesRolesPostRequest.employeeId()));
        EmployeesRoles employeesRolesData = rolesEmployeesMapper.toEmployeesRoles(employeesRolesPostRequest);
        employeesRolesData.setRole(roleData);
        employeesRolesData.setEmployee(employeeData);
        return employeesRolesRepository.save(employeesRolesData);
    }

    public void delete(Long id){
        EmployeesRoles employeesRolesData = rolesEmployeesMapper.toEmployeesRoles(findById(id));
        employeesRolesRepository.delete(employeesRolesData);
    }

}
