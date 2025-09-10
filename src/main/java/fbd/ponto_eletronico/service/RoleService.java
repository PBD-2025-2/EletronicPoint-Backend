package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.RoleDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.mapper.RoleMapper;
import fbd.ponto_eletronico.repository.RoleRepository;
import fbd.ponto_eletronico.request.RolePostRequest;
import fbd.ponto_eletronico.request.RolePutRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final CompanyService companyService;
    private  final CompanyMapper companyMapper;

    public List<RoleDTO> listAll(){
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleDtos(roles);
    }

    public RoleDTO findById(Long id) {
        Optional<Role> roleData = roleRepository.findById(id);
        return roleMapper.toRoleDto(roleData.orElseThrow(() -> new BadRequestException("Id not Found")));
    }

    public List<RoleDTO> findByName(String name) {
        List<Role> roleData = roleRepository.findByName(name);
        return roleMapper.toRoleDtos(roleData);
    }

    public List<RoleDTO> findByCompany(String cnpj) {
        List<Company> companiesData = companyMapper.toCompanies(companyService.findByCnpj(cnpj));
        List<Role> roleData = roleRepository.findByCompany(companiesData.getFirst());
        return roleMapper.toRoleDtos(roleData);
    }

    public List<RoleDTO> findByRoleNameAndCnpj(String name, String cnpj){
        List<RoleDTO> rolesData = findByName(name);
        List<RoleDTO> filterByCnpj = rolesData.stream().filter(
                roleCNPJs -> roleCNPJs.company().cnpj().equalsIgnoreCase(cnpj)).toList();
        if(filterByCnpj.isEmpty()){
            throw new BadRequestException("This role not exists in this company");
        }
        return filterByCnpj;

    }

    @Transactional
    public Role save(RolePostRequest rolePostRequest) {
        Company companyData = companyMapper.toCompany(companyService.findById(rolePostRequest.companyId()));
        if(roleRepository.existsRoleByNameAndCompany(rolePostRequest.name(), companyData)){
            throw new BadRequestException("This role exists in this company");
        }
        Role roleData = roleMapper.toRole(rolePostRequest);
        roleData.setCompany(companyData);
        return roleRepository.save(roleData);
    }

    public Role replace(Long id, RolePutRequest rolePutRequest) {
        Company companyData = companyMapper.toCompany(companyService.findById(rolePutRequest.companyId()));
        Role roleData = roleMapper.toRole(findById(id));
        Role roleReplace = roleMapper.toRole(rolePutRequest);
        roleReplace.setId(roleData.getId());
        roleReplace.setCompany(companyData);
        return roleRepository.save(roleReplace);
    }

    public void delete(Long id){
        Role roleData = roleMapper.toRole(findById(id));
        roleRepository.delete(roleData);
    }
}
