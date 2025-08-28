package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.RoleDTO;
import fbd.ponto_eletronico.entity.Role;
import fbd.ponto_eletronico.mapper.RoleMapper;
import fbd.ponto_eletronico.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleDTO> listAll(){
        List<Role> roles = roleRepository.findAll();
        return roleMapper.roleDtos(roles);
    }

}
