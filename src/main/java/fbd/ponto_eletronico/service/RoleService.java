package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.RoleDTO;
import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.CompanyMapper;
import fbd.ponto_eletronico.mapper.RoleMapper;
import fbd.ponto_eletronico.repository.RoleRepository;
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
        return roleData.map(roleMapper :: toRoleDto)
                .orElseThrow(() -> new BadRequestException("Id not Found"));
    }

    public List<RoleDTO> findByName(String name) {
        List<Role> roleData = roleRepository.findByName(name);
        return roleMapper.toRoleDtos(roleData);
    }

    public List<RoleDTO> findByCnpj(String cnpj) {
        List<Company> companiesData = companyMapper.toCompanies(companyService.findByCnpj(cnpj));
        List<Role> roleData = roleRepository.findByCompany(companiesData.getFirst());
        return roleMapper.toRoleDtos(roleData);
    }
}
