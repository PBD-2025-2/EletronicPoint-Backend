package pbd.ponto_eletronico.service;

import pbd.ponto_eletronico.dto.EletronicPointsDTO;
import pbd.ponto_eletronico.entity.EletronicPoints;
import pbd.ponto_eletronico.entity.EmployeesRoles;
import pbd.ponto_eletronico.enums.TypeRoster;
import pbd.ponto_eletronico.exception.BadRequestException;
import pbd.ponto_eletronico.mapper.EletronicPointsMapper;
import pbd.ponto_eletronico.mapper.EmployeesRolesMapper;
import pbd.ponto_eletronico.repository.EletronicPointsRepository;
import pbd.ponto_eletronico.repository.EmployeesRolesRepository;
import pbd.ponto_eletronico.request.EletronicPointsPostRequest;
import pbd.ponto_eletronico.request.EletronicPointsPutRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        List<EletronicPoints> eletronicPointsListById = eletronicPointsRepository.findByEmployeesRoles_IdOrderByIdAsc(employeeRolesId);

        if(!eletronicPointsListById.isEmpty()) {
            EletronicPoints eletronicPointsData = eletronicPointsRepository.findByEmployeesRoles_IdOrderByIdAsc(employeeRolesId).getLast();
            return registerExistingEletronicPoint(eletronicPointsData);
        }else {
            return registerNewEletronicPoint(employeeRolesId);
        }
    }

    public EletronicPointsDTO replace(Long eletronicPointsId, Long employeesRolesId, EletronicPointsPutRequest eletronicPointsPutRequest){
        EmployeesRoles employeesRolesData = employeesRolesRepository.getReferenceById(employeesRolesId);
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

        EletronicPointsPostRequest eletronicPointsPostRequestData = new EletronicPointsPostRequest(employeeRolesId, dateNow, timeNow, 1);

        EletronicPoints firstEletronicPoint = eletronicPointsMapper.toEletronicPoints(eletronicPointsPostRequestData);
        firstEletronicPoint.setEmployeesRoles(employeesRolesData);
        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsRepository.save(firstEletronicPoint));
    }

    private EletronicPointsDTO registerExistingEletronicPoint(EletronicPoints eletronicPoints) {
        List<LocalTime> oldRegistersCurrent = findCurrentRegisterPoints(eletronicPoints);
        TypeRoster workRegime = eletronicPoints.getEmployeesRoles().getRoster().getType();

        if (isPending(eletronicPoints)) {
            return applyPedingStatus(eletronicPoints);
        }

        if((oldRegistersCurrent.get(3) != null && (workRegime == TypeRoster.Diaria)) ||
            (oldRegistersCurrent.get(1) != null && (workRegime == TypeRoster.Plantão))){
            Long id = eletronicPoints.getEmployeesRoles().getId();
            return registerNewEletronicPoint(id);
        }

        if (workRegime == TypeRoster.Diaria) {
            registerDailySchedule(eletronicPoints, oldRegistersCurrent);
        }

        if (workRegime == TypeRoster.Plantão) {
            registerDutySchedule(eletronicPoints, oldRegistersCurrent);
        }

        return replace(eletronicPoints.getId(),eletronicPoints.getEmployeesRoles().getId(), eletronicPointsMapper.eletronicPointsToEletronicPointsPutRequest(eletronicPoints));

    }

    private List<LocalTime> findCurrentRegisterPoints(EletronicPoints eletronicPoints) {
        List<LocalTime> currentRegisterPoints = new ArrayList<>();
        currentRegisterPoints.add(eletronicPoints.getRegister_1());
        currentRegisterPoints.add(eletronicPoints.getRegister_2());
        currentRegisterPoints.add(eletronicPoints.getRegister_3());
        currentRegisterPoints.add(eletronicPoints.getRegister_4());
        log.info(currentRegisterPoints);
        return currentRegisterPoints;
    }

    private void closeRegister(EletronicPoints eletronicPoints) {
        eletronicPoints.setEndDate(LocalDate.now());
        eletronicPoints.setStatus(2);
    }

    private boolean isPending(EletronicPoints eletronicPoints) {
        int weeklyWorkload = eletronicPoints.getEmployeesRoles().getRoster().getWeeklyWorkload();
        TypeRoster workRegime = eletronicPoints.getEmployeesRoles().getRoster().getType();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDateAndFirstRegister = LocalDateTime.of(eletronicPoints.getStartDate(), eletronicPoints.getRegister_1());
        long durationBetweenDates = Duration.between(startDateAndFirstRegister, now).toHours();

        if ((workRegime == TypeRoster.Diaria) &&
            (now.toLocalDate().isAfter(eletronicPoints.getStartDate()) ||
            eletronicPoints.getRegister_4() != null && (durationBetweenDates < weeklyWorkload))) {
            return true;
        }

        if ((workRegime == TypeRoster.Plantão) && durationBetweenDates < weeklyWorkload) {
            return true;
        }

        return false;
    }

    private EletronicPointsDTO applyPedingStatus(EletronicPoints eletronicPoints) {
        switch (eletronicPoints.getEmployeesRoles().getRoster().getType()) {
            case TypeRoster.Diaria -> eletronicPoints.setStatus(3);

            case TypeRoster.Plantão -> {
                eletronicPoints.setStatus(3);
                eletronicPoints.setRegister_2(LocalTime.now());
            }
        }

        eletronicPoints.setEndDate(LocalDate.now());
        replace(eletronicPoints.getId(),eletronicPoints.getEmployeesRoles().getId(),
                eletronicPointsMapper.eletronicPointsToEletronicPointsPutRequest(eletronicPoints));
        return registerNewEletronicPoint(eletronicPoints.getEmployeesRoles().getId());
    }

    private void registerDailySchedule(EletronicPoints eletronicPoints, List<LocalTime> actualRegisters) {
        LocalTime now = LocalTime.now();

        if (actualRegisters.get(0) == null) {
            eletronicPoints.setRegister_1(now);
        }else if(actualRegisters.get(1) == null) {
            eletronicPoints.setRegister_2(now);
        }else if(actualRegisters.get(2) == null) {
            eletronicPoints.setRegister_3(now);
        }else if(actualRegisters.get(3) == null){
            eletronicPoints.setRegister_4(now);
            closeRegister(eletronicPoints);
            replace(eletronicPoints.getId(),eletronicPoints.getEmployeesRoles().getId(), eletronicPointsMapper.eletronicPointsToEletronicPointsPutRequest(eletronicPoints));
        }
    }

    private void registerDutySchedule(EletronicPoints eletronicPoints, List<LocalTime> actualRegisters) {
        LocalTime now = LocalTime.now();

        if (actualRegisters.getFirst() == null) {
            eletronicPoints.setRegister_1(now);
        }

        if (actualRegisters.get(1) == null) {
            eletronicPoints.setRegister_2(now);
            closeRegister(eletronicPoints);
        }
    }
}