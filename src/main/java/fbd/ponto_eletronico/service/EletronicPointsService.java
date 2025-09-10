package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.entity.EletronicPoints;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.EletronicPointsMapper;
import fbd.ponto_eletronico.mapper.EmployeesRolesMapper;
import fbd.ponto_eletronico.repository.EletronicPointsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class EletronicPointsService {

    private final EletronicPointsRepository eletronicPointsRepository;
    private final EletronicPointsMapper eletronicPointsMapper;
    private final EmployeesRolesService employeesRolesService;
    private final EmployeesRolesMapper employeesRolesMapper;

    public List<EletronicPointsDTO> listAll() {
        List<EletronicPoints> eletronicPoints = eletronicPointsRepository.findAll();
        return eletronicPointsMapper.toEletronicPointsDto(eletronicPoints);
    }

    public EletronicPointsDTO findById(Long id){
        Optional<EletronicPoints> eletronicPointsData = eletronicPointsRepository.findById(id);
        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsData.
                orElseThrow(() -> new BadRequestException("Id Not Found")));
    }

    public List<EletronicPointsDTO> findByEmployee(String cpf){
        List<EletronicPoints> eletronicPointsData = eletronicPointsRepository.findByEmployeesRoles_Employee_Cpf(cpf);

        if (eletronicPointsData.isEmpty()) {
            throw new BadRequestException("This employee has no records at the bank");
        }

        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsData);
    }

    public List<EletronicPointsDTO> findByCompany(String cnpj){
        List<EletronicPoints> eletronicPointsData = eletronicPointsRepository.findByEmployeesRoles_Role_Company_Cnpj(cnpj);

        if (eletronicPointsData.isEmpty()) {
            throw new BadRequestException("This employee has no records at the bank");
        }

        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsData);
    }

    public List<EletronicPointsDTO> findByBate(LocalDate localDate){
        List<EletronicPoints> eletronicPointsData = eletronicPointsRepository.findByStartDate(localDate);

        if (eletronicPointsData.isEmpty()) {
            throw new BadRequestException("This employee has no records at the date");
        }

        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsData);
    }

    public List<EletronicPointsDTO> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate){
        List<EletronicPoints> eletronicPointsData = eletronicPointsRepository.findByStartDateBetween(startDate, endDate);

        if (eletronicPointsData.isEmpty()) {
            throw new BadRequestException("This employee has no records at the period");
        }

        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsData);
    }
}