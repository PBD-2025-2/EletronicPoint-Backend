package pbd.ponto_eletronico.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbd.ponto_eletronico.dto.EmployeesRolesDTO;
import pbd.ponto_eletronico.entity.Employee;
import pbd.ponto_eletronico.entity.EmployeesRoles;
import pbd.ponto_eletronico.entity.Role;
import pbd.ponto_eletronico.entity.Roster;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.EmployeeMapper;
import pbd.ponto_eletronico.mapper.EmployeesRolesMapper;
import pbd.ponto_eletronico.mapper.RoleMapper;
import pbd.ponto_eletronico.mapper.RosterMapper;
import pbd.ponto_eletronico.repository.EmployeesRolesRepository;
import pbd.ponto_eletronico.request.EmployeesRolesPostRequest;
import pbd.ponto_eletronico.request.EmployeesRolesPutRequest;
import pbd.ponto_eletronico.util.FilterActivesRoles;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesRolesService {
    private final EmployeesRolesRepository employeesRolesRepository;
    private final EmployeesRolesMapper employeesRolesMapper;
    private final RoleMapper roleMapper;
    private final RoleService roleService;
    private final RosterMapper rosterMapper;
    private final RosterService rosterService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    public List<EmployeesRolesDTO> listAll() {
        List<EmployeesRoles> employeesRoles = employeesRolesRepository.findAll();
        if(employeesRoles.isEmpty()){
            throw new BadRequestException("Employees Roles not found!");
        }
        return employeesRolesMapper.toEmployeesRolesDtos(employeesRoles);
    }

    public EmployeesRolesDTO findById(Long id){
        Optional<EmployeesRoles> employeesRolesData = employeesRolesRepository.findById(id);
        return employeesRolesMapper.toEmployeeRolesDto(employeesRolesData.
                orElseThrow(() -> new BadRequestException("Id Not Found")));
    }

    public List<EmployeesRolesDTO> findByEmployeeByCpf(String cpf){
        Employee employeesData = employeeMapper.toEmployee(employeeService.findByCpf(cpf));
        if(employeesData == null){
            throw new BadRequestException("Employees Roles with this employee not found!");
        }
        List<EmployeesRoles> employeesRolesData = employeesRolesRepository.findByEmployee(employeesData);
        return employeesRolesMapper.toEmployeesRolesDtos(employeesRolesData);
    }

    public List<EmployeesRolesDTO> findByEmployeeId(Long id ) {
        List<EmployeesRoles> employeesRolesData = employeesRolesRepository.findByEmployee_Id(id);
        return employeesRolesMapper.toEmployeesRolesDtos(employeesRolesData);
    }

    public List<EmployeesRolesDTO> findByEmployeeCpfAndRoleName(String cpf, String roleName){
        List<EmployeesRoles> employeesRolesData = employeesRolesMapper.toEmployeesRoles(findByEmployeeByCpf(cpf));
        if(employeesRolesData.isEmpty()){
            throw new BadRequestException("Employees Roles with this cpf not found!");
        }
        List<EmployeesRoles> filterRolesName = employeesRolesData.stream()
                        .filter(employeesRoles -> employeesRoles.
                                getRole().getName().equalsIgnoreCase(roleName)).toList();
        if(filterRolesName.isEmpty()){
            throw new BadRequestException("Employees Roles with this role name not found!");
        }
        return employeesRolesMapper.toEmployeesRolesDtos(filterRolesName);

    }

    @Transactional
    public EmployeesRoles save(EmployeesRolesPostRequest employeesRolesPostRequest){
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeesRolesPostRequest.employeeId()));
        Role roleData = roleMapper.toRole(roleService.findById(employeesRolesPostRequest.roleId()));
        Roster rosterData = rosterMapper.rosterDTOToRoster(rosterService.findById(employeesRolesPostRequest.idRoster()));

        if (existEmployeeRole(employeesRolesPostRequest.idRoster(), employeeData, roleData)) {
            throw new BadRequestException("this role is already registered ");
        }
        List<EmployeesRoles> allRolesEmployee = employeesRolesMapper.toEmployeesRoles(findByEmployeeByCpf(employeeData.getCpf()));

        if(employeesRolesPostRequest.status() && FilterActivesRoles.filterActivesRoles(allRolesEmployee).size() >= 2){
            throw new BadRequestException("employee has two active positions, cannot perform another function");
        }

        EmployeesRoles employeesRolesData = employeesRolesMapper.toEmployeesRoles(employeesRolesPostRequest);

        employeesRolesData.setRole(roleData);
        employeesRolesData.setEmployee(employeeData);
        employeesRolesData.setRoster(rosterData);
        return employeesRolesRepository.save(employeesRolesData);
    }

    public EmployeesRoles replace(Long id, EmployeesRolesPutRequest employeeRolesPutRequest){
        EmployeesRoles employeesRoles = employeesRolesMapper.toEmployeesRoles(findById(id));
        Roster rosterData = rosterMapper.rosterDTOToRoster(rosterService.findById(employeeRolesPutRequest.idRoster()));
        Employee employeeData = employeeMapper.toEmployee(employeeService.findById(employeeRolesPutRequest.employeeId()));
        Role roleData = roleMapper.toRole(roleService.findById(employeeRolesPutRequest.roleId()));

        List<EmployeesRoles> allRolesEmployee = employeesRolesMapper.toEmployeesRoles(findByEmployeeByCpf(employeeData.getCpf()));

        if(employeeRolesPutRequest.status() && FilterActivesRoles.filterActivesRoles(allRolesEmployee).size() >= 2){
            throw new BadRequestException("employee has two active positions, cannot perform another function");
        }

        EmployeesRoles employeesRolesReplace = employeesRolesMapper.toEmployeesRolesPut(employeeRolesPutRequest);

        employeesRolesReplace.setId(employeesRoles.getId());
        employeesRolesReplace.setRoster(rosterData);
        employeesRolesReplace.setRole(roleData);
        employeesRolesReplace.setEmployee(employeeData);

        return employeesRolesRepository.save(employeesRolesReplace);
    }

    public void delete(Long id){
        EmployeesRoles employeesRolesData = employeesRolesMapper.toEmployeesRoles(findById(id));
        employeesRolesRepository.delete(employeesRolesData);
    }

    private boolean existEmployeeRole(Long  idRoster, Employee employee, Role role) {
        return employeesRolesRepository.existsByRoster_IdAndEmployeeAndRole(idRoster, employee, role);
    }

}
