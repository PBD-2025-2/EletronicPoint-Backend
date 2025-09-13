package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
import fbd.ponto_eletronico.dto.EmployeesRolesDTO;
import fbd.ponto_eletronico.entity.EletronicPoints;
import fbd.ponto_eletronico.entity.EmployeesRoles;
import fbd.ponto_eletronico.exception.BadRequestException;
import fbd.ponto_eletronico.mapper.EletronicPointsMapper;
import fbd.ponto_eletronico.mapper.EmployeesRolesMapper;
import fbd.ponto_eletronico.repository.EletronicPointsRepository;
import fbd.ponto_eletronico.repository.EmployeesRolesRepository;
import fbd.ponto_eletronico.request.EletronicPointsPostRequest;
import fbd.ponto_eletronico.request.EletronicPointsPutRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class EletronicPointsService {

    private final EletronicPointsRepository eletronicPointsRepository;
    private final EletronicPointsMapper eletronicPointsMapper;
    private final EmployeesRolesService employeesRolesService;
    private final EmployeesRolesMapper employeesRolesMapper;
    private final EmployeesRolesRepository employeesRolesRepository;

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

    public EletronicPointsDTO register(Long employeeRolesId){
        EletronicPoints eletronicPointsData = eletronicPointsRepository.findByEmployeesRoles_Id(employeeRolesId).getLast();

        if(eletronicPointsData != null) {
//           Registrar ponto já existente
        }

        return registerNewEletronicPoint(employeeRolesId);
    }

    public EletronicPointsDTO replace(Long eletronicPointsId, EletronicPointsPutRequest eletronicPointsPutRequest){
        EmployeesRoles employeesRolesData = employeesRolesRepository.getReferenceById(eletronicPointsPutRequest.employeeRolesId());
        EletronicPoints eletronicPoints = eletronicPointsRepository.getReferenceById(eletronicPointsId);
        EletronicPoints eletronicPointsReplaceDate = eletronicPointsMapper.eletronicPointsPutRequestToEletronicPoints(eletronicPointsPutRequest);
        eletronicPointsReplaceDate.setId(eletronicPoints.getId());
        eletronicPointsReplaceDate.setEmployeesRoles(employeesRolesData);

        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsRepository.save(eletronicPointsReplaceDate));
    }

    private EletronicPointsDTO registerNewEletronicPoint(Long employeeRolesId){
        EmployeesRoles employeesRolesData = employeesRolesMapper.toEmployeesRoles(employeesRolesService.findById(employeeRolesId));
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();

        EletronicPointsPostRequest eletronicPointsPostRequestData = new EletronicPointsPostRequest(
                employeeRolesId,
                dateNow,
                timeNow,
                1
        );

        EletronicPoints firstEletronicPoint = eletronicPointsMapper.toEletronicPoints(eletronicPointsPostRequestData);
        firstEletronicPoint.setEmployeesRoles(employeesRolesData);
        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsRepository.save(firstEletronicPoint));
    }

//    private registerExistingEletronicPoint(EletronicPoints eletronicPoints) {
//        LocalDate dateNow = LocalDate.now();
//        LocalTime timeNow = LocalTime.now();
//        List<LocalTime> oldRegistersCurrent = findCurrentRegisterPoints(eletronicPoints);
//
//        // Verificar se todos os elementos da lista são nulos se não for achar o próximo nulo para ver onde registrar
//    }

    private List<LocalTime> findCurrentRegisterPoints(EletronicPoints eletronicPoints) {
        List<LocalTime> currentRegisterPoints = new ArrayList<>();
        currentRegisterPoints.add(eletronicPoints.getRegister_1());
        currentRegisterPoints.add(eletronicPoints.getRegister_2());
        currentRegisterPoints.add(eletronicPoints.getRegister_3());
        currentRegisterPoints.add(eletronicPoints.getRegister_4());

        return currentRegisterPoints;
    }
}