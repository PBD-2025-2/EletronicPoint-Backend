package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
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
import fbd.ponto_eletronico.request.EmployeesRolesPutRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeesRolesPostRequest.employeeId()));
        if(employeesRolesPostRequest.status()){
            List<EmployeesRolesDTO> AllRolesEmployee = findByEmployee(employeeData.getCpf());
            List<EmployeesRolesDTO> rolesActiveEmployee = AllRolesEmployee.stream().filter(EmployeesRolesDTO::status).toList();
            if(rolesActiveEmployee.size() >= 2){
                throw new BadRequestException("employee has two active positions, cannot perform another function");
            }
        }
        Role roleData = roleMapper.toRole(roleService.findById(employeesRolesPostRequest.roleId()));
        EmployeesRoles employeesRolesData = rolesEmployeesMapper.toEmployeesRoles(employeesRolesPostRequest);
        employeesRolesData.setRole(roleData);
        employeesRolesData.setEmployee(employeeData);
        return employeesRolesRepository.save(employeesRolesData);
    }

    public EmployeesRoles replace(Long id, EmployeesRolesPutRequest employeeRolesPutRequest){
        EmployeesRoles employeesRoles = rolesEmployeesMapper.toEmployeesRoles(findById(id));
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeeRolesPutRequest.employeeId()));
        EmployeesRoles employeesRolesReplace = rolesEmployeesMapper.employeesRolesPut(employeeRolesPutRequest);
        employeesRolesReplace.setId(employeesRoles.getId());
        employeesRolesReplace.setEmployee(employeeData);
        employeesRolesReplace.setRole(employeesRoles.getRole());
        return employeesRolesRepository.save(employeesRolesReplace);
    }

    public void delete(Long id){
        EmployeesRoles employeesRolesData = rolesEmployeesMapper.toEmployeesRoles(findById(id));
        employeesRolesRepository.delete(employeesRolesData);
    }

}
