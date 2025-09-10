package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.mapper.EmployeeMapper;
import fbd.ponto_eletronico.mapper.RoleMapper;
import fbd.ponto_eletronico.mapper.EmployeesRolesMapper;
import fbd.ponto_eletronico.repository.EmployeesRolesRepository;
import fbd.ponto_eletronico.repository.RoleRepository;
import fbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import fbd.ponto_eletronico.request.EmployeesRolesPutRequest;
import fbd.ponto_eletronico.util.FilterActivesRoles;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesRolesService {
    private final EmployeesRolesRepository employeesRolesRepository;
    private final EmployeesRolesMapper employeesRolesMapper;
    private final CompanyMapper companyMapper;
    private final CompanyService companyService;
    private final RoleMapper roleMapper;
    private final RoleService roleService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;
    private final RoleRepository roleRepository;

    public List<EmployeesRolesDTO> listAll() {
        List<EmployeesRoles> employeesRoles = employeesRolesRepository.findAll();
        return employeesRolesMapper.toEmployeesRolesDtos(employeesRoles);
    }

    public EmployeesRolesDTO findById(Long id){
        Optional<EmployeesRoles> employeesRolesData = employeesRolesRepository.findById(id);
        return employeesRolesMapper.toEmployeeRolesDto(employeesRolesData.
                orElseThrow(() -> new BadRequestException("Id Not Found")));
    }

    public List<EmployeesRolesDTO> findByEmployee(String cpf){
        List<Employee> employeesData = employeeMapper.toEmployees(employeeService.findByCpf(cpf));
        List<EmployeesRoles> employeesRolesData = employeesRolesRepository.findByEmployee(employeesData.getFirst());
        return employeesRolesMapper.toEmployeesRolesDtos(employeesRolesData);
    }

    public List<EmployeesRolesDTO> findByEmployeeRole(String cpf, String roleName){
        List<EmployeesRoles> employeesRolesData = employeesRolesMapper.toEmployeesRoles(findByEmployee(cpf));
        List<EmployeesRoles> filterRolesName = employeesRolesData.stream()
                        .filter(employeesRoles -> employeesRoles.
                                getRole().getName().equalsIgnoreCase(roleName)).toList();

        return employeesRolesMapper.toEmployeesRolesDtos(filterRolesName);

    }

    @Transactional
    public EmployeesRoles save(EmployeesRolesPostRequest employeesRolesPostRequest){
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeesRolesPostRequest.employeeId()));
        Role roleData = roleMapper.toRole(roleService.findById(employeesRolesPostRequest.roleId()));

        if (existEmployeeRole(employeesRolesPostRequest.workRegime(), employeeData, roleData)) {
            throw new BadRequestException("this role is already registered ");
        }
        List<EmployeesRoles> allRolesEmployee = employeesRolesMapper.toEmployeesRoles(findByEmployee(employeeData.getCpf()));

        if(employeesRolesPostRequest.status() && FilterActivesRoles.filterActivesRoles(allRolesEmployee).size() >= 2){
            throw new BadRequestException("employee has two active positions, cannot perform another function");
        }

        EmployeesRoles employeesRolesData = employeesRolesMapper.toEmployeesRoles(employeesRolesPostRequest);

        employeesRolesData.setRole(roleData);
        employeesRolesData.setEmployee(employeeData);
        return employeesRolesRepository.save(employeesRolesData);
    }

    public EmployeesRoles replace(Long id, EmployeesRolesPutRequest employeeRolesPutRequest){
        EmployeesRoles employeesRoles = employeesRolesMapper.toEmployeesRoles(findById(id));
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeeRolesPutRequest.employeeId()));
        EmployeesRoles employeesRolesReplace = employeesRolesMapper.toEmployeesRolesPut(employeeRolesPutRequest);
        employeesRolesReplace.setId(employeesRoles.getId());
        employeesRolesReplace.setEmployee(employeeData);
        employeesRolesReplace.setRole(employeesRoles.getRole());
        return employeesRolesRepository.save(employeesRolesReplace);
    }

    public void delete(Long id){
        EmployeesRoles employeesRolesData = employeesRolesMapper.toEmployeesRoles(findById(id));
        employeesRolesRepository.delete(employeesRolesData);
    }

    private boolean existEmployeeRole(int  workRegime, Employee employee, Role role) {
        return employeesRolesRepository.existsByWorkRegimeAndEmployeeAndRole(workRegime, employee, role);
    }

}
