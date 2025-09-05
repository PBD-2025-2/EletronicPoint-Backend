package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EmployeeDTO;
import fbd.ponto_eletronico.dto.EmployeesCompaniesDTO;
import fbd.ponto_eletronico.entity.Employee;
import fbd.ponto_eletronico.entity.EmployeesCompanies;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.EmployeesCompaniesMapper;
import fbd.ponto_eletronico.repository.EmployeesCompaniesRepository;
import fbd.ponto_eletronico.request.EmployeePostRequest;
import fbd.ponto_eletronico.request.EmployeePutRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesCompaniesService {
    private final EmployeesCompaniesRepository employeesCompaniesRepository;
    private final EmployeesCompaniesMapper employeesCompaniesMapper;

    public List<EmployeesCompaniesDTO> findAll(){
        List<EmployeesCompanies> employeesCompanies = employeesCompaniesRepository.findAll();
        List<EmployeesCompaniesDTO> employeesCompaniesDTO = employeesCompaniesMapper.toEmployeesCompaniesDtos(employeesCompanies);
        return employeesCompaniesDTO;
    }

    public EmployeesCompaniesDTO findById(Long id) {
        Optional<EmployeesCompanies> employeesCompaniesData = employeesCompaniesRepository.findById(id);
        return employeesCompaniesData.map(employeesCompaniesMapper :: toEmployeesCompaniesDto)
                .orElseThrow(() -> new BadRequestException("Id not Found"));
    }


}
