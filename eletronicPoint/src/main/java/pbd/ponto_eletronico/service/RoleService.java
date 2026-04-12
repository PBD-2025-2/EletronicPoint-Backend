package pbd.ponto_eletronico.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import pbd.ponto_eletronico.dto.RoleDTO;
import pbd.ponto_eletronico.entity.Role;
import pbd.ponto_eletronico.entity.Sectors;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.CompanyMapper;
import pbd.ponto_eletronico.mapper.RoleMapper;
import pbd.ponto_eletronico.mapper.SectorsMapper;
import pbd.ponto_eletronico.repository.RoleRepository;
import pbd.ponto_eletronico.request.RolePostRequest;
import pbd.ponto_eletronico.request.RolePutRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final SectorsService sectorsService;
    private final SectorsMapper sectorsMapper;


    public List<RoleDTO.Summary> listAll(){
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleSummaryDtos(roles);
    }

    public RoleDTO findById(Long id) {
        Role roleData = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with this ID!"));

        return roleMapper.toRoleDto(roleData);
    }

    public List<RoleDTO> findByName(String name) {
        List<Role> roleData = roleRepository.findByName(name);
        return roleMapper.toRoleDtos(roleData);
    }

    public List<RoleDTO> findByRoleNameAndCompanyId(String roleName, Long companyId){
        List<Role> rolesData = roleRepository.findByNameAndSectors_Company_Id(roleName, companyId);
        return roleMapper.toRoleDtos(rolesData);
    }

    public List<RoleDTO> findByCompanyId(Long companyId){
        List<Role> rolesData = roleRepository.findBySectors_Company_Id(companyId);
        return roleMapper.toRoleDtos(rolesData);
    }

    public List<RoleDTO> findByCompanyCnpj(String companyCnpj){
        List<Role> rolesData = roleRepository.findBySectors_Company_Cnpj(companyCnpj);
        return roleMapper.toRoleDtos(rolesData);
    }

    public List<RoleDTO> findByRoleNameAndSectors_Name(String name, String sectorName){
        List<RoleDTO> rolesData = findByName(name);
        List<RoleDTO> filterBySectorName = rolesData.stream().filter(
                roles -> roles.sectors().name().equalsIgnoreCase(sectorName)).toList();

        if(filterBySectorName.isEmpty())
            throw new EntityNotFoundException("Role not found with this sector name!");

        return filterBySectorName;
    }

    @Transactional
    public RoleDTO save(RolePostRequest rolePostRequest) {
        Sectors sectorsData = sectorsMapper.sectorsDTOToSectors(sectorsService.findById(rolePostRequest.sectorId()));

        if(existRole(rolePostRequest.name(), sectorsData))
            throw new EntityExistsException("This role already exists in this company");

        if (rolePostRequest.responsibility() == null || (rolePostRequest.baseSalary() == null))
            throw new BadRequestException("Responsibility or Base Salary must not be null");

        if (rolePostRequest.baseSalary().compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Base Salary must be positive");

        Role roleData = roleMapper.toRole(rolePostRequest);
        roleData.setSectors(sectorsData);
        return roleMapper.toRoleDto(roleRepository.save(roleData));
    }

    public Role replace(Long id, RolePutRequest rolePutRequest) {
        if (rolePutRequest.responsibility() == null || (rolePutRequest.baseSalary() == null))
            throw new BadRequestException("Responsibility or Base Salary must not be null");

        if (rolePutRequest.baseSalary().compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Base Salary must be positive");

        Sectors sectorsData = sectorsMapper.sectorsDTOToSectors(sectorsService.findById(rolePutRequest.sectorId()));
        Role roleData = roleMapper.toRole(findById(id));
        Role roleReplace = roleMapper.toRole(rolePutRequest);
        roleReplace.setBaseSalary(rolePutRequest.baseSalary());
        roleReplace.setId(roleData.getId());
        roleReplace.setSectors(sectorsData);
        return roleRepository.save(roleReplace);
    }

    public void delete(Long id){
        Role roleData = roleMapper.toRole(findById(id));
        roleRepository.delete(roleData);
    }

    private boolean existRole(String name, Sectors sector) {
        return roleRepository.existsRoleByNameAndSectors(name, sector);
    }
}
