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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;

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
        List<EletronicPoints> eletronicPointsListByEmployeesRoles = eletronicPointsRepository.findByEmployeesRoles_Id(employeeRolesId);
        if(eletronicPointsListByEmployeesRoles.isEmpty()) {
            return registerNewEletronicPoint(employeeRolesId);
        }
        EletronicPoints eletronicPoints = eletronicPointsRepository.findByEmployeesRoles_Id(employeeRolesId).getLast();
        List<LocalTime> registers = Arrays.asList(eletronicPoints.getRegister_2(), eletronicPoints.getRegister_3(), eletronicPoints.getRegister_4());
        if(registers.get(0) == null){
            eletronicPoints.setRegister_2(LocalTime.now());
        }else if(registers.get(1) == null){
            eletronicPoints.setRegister_4(LocalTime.now());
        }

        return eletronicPointsMapper.toEletronicPointsDto(eletronicPoints);
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
        EletronicPointsPostRequest eletronicPointsPostRequestData = new EletronicPointsPostRequest(employeesRolesData, dateNow, timeNow, 1);
        EletronicPoints firstEletronicPoint = eletronicPointsMapper.toEletronicPoints(eletronicPointsPostRequestData);
        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsRepository.save(firstEletronicPoint));
    }

//    List<LocalTime> registers = Arrays.asList(eletronicPoints.getRegister_2(), eletronicPoints.getRegister_3(), eletronicPoints.getRegister_4());
//        if(registers.get(0) == null){
//        eletronicPoints.setRegister_2(LocalTime.now());
//    }else if(registers.get(1) == null){
//        eletronicPoints.setRegister_4(LocalTime.now());
//    }


}