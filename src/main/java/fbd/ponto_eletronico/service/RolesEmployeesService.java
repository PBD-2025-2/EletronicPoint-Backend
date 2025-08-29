package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.RolesEmployeesDTO;
import fbd.ponto_eletronico.entity.RolesEmployees;
import fbd.ponto_eletronico.mapper.RolesEmployeesMapper;
import fbd.ponto_eletronico.repository.RolesEmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesEmployeesService {
    private final RolesEmployeesRepository rolesEmployeesRepository;
    private final RolesEmployeesMapper rolesEmployeesMapper;

    public List<RolesEmployeesDTO> listAll() {
        List<RolesEmployees> rolesEmployees = rolesEmployeesRepository.findAll();
        return rolesEmployeesMapper.rolesEmployeesDtos(rolesEmployees);
    }
}
