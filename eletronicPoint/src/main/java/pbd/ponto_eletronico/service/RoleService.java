package pbd.ponto_eletronico.service;

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

    public List<RoleDTO> findBySector(String sectorName){
        List<Role> rolesData = roleRepository.findBySectors_Name(sectorName);
        return roleMapper.toRoleDtos(rolesData);
    }

    public List<RoleDTO> findByRoleNameAndSectors_Name(String name, String sectorName){
        List<RoleDTO> rolesData = findByName(name);
        List<RoleDTO> filterBySectorName = rolesData.stream().filter(
                roles -> roles.sectors().name().equalsIgnoreCase(sectorName)).toList();
        if(filterBySectorName.isEmpty()){
            throw new BadRequestException("This role not exists in this company");
        }
        return filterBySectorName;

    }

    @Transactional
    public RoleDTO save(RolePostRequest rolePostRequest) {
        Sectors sectorsData = sectorsMapper.sectorsDTOToSectors(sectorsService.findById(rolePostRequest.sectorId()));

        if(existRole(rolePostRequest.name(), sectorsData)){
            throw new BadRequestException("This role exists in this company");
        }

        Role roleData = roleMapper.toRole(rolePostRequest);
        roleData.setSectors(sectorsData);
        return roleMapper.toRoleDto(roleRepository.save(roleData));
    }

    public Role replace(Long id, RolePutRequest rolePutRequest) {
        Sectors sectorsData = sectorsMapper.sectorsDTOToSectors(sectorsService.findById(rolePutRequest.sectorId()));
        Role roleData = roleMapper.toRole(findById(id));
        Role roleReplace = roleMapper.toRole(rolePutRequest);
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
