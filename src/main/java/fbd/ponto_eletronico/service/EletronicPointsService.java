package fbd.ponto_eletronico.service;

import fbd.ponto_eletronico.dto.EletronicPointsDTO;
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
        EletronicPoints eletronicPointsData = eletronicPointsRepository.findByEmployeesRoles_Id(employeeRolesId).getLast();

        if(eletronicPointsData != null) {
            return registerExistingEletronicPoint(eletronicPointsData);
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

        EletronicPointsPostRequest eletronicPointsPostRequestData = new EletronicPointsPostRequest(employeeRolesId, dateNow, timeNow, 1);

        EletronicPoints firstEletronicPoint = eletronicPointsMapper.toEletronicPoints(eletronicPointsPostRequestData);
        firstEletronicPoint.setEmployeesRoles(employeesRolesData);
        return eletronicPointsMapper.toEletronicPointsDto(eletronicPointsRepository.save(firstEletronicPoint));
    }

    private EletronicPointsDTO registerExistingEletronicPoint(EletronicPoints eletronicPoints) {
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        List<LocalTime> oldRegistersCurrent = findCurrentRegisterPoints(eletronicPoints);

        if (oldRegistersCurrent.stream().allMatch(Objects::nonNull)) {
            closeRegister(eletronicPoints);
            replace(eletronicPoints.getId(), eletronicPointsMapper.eletronicPointsToEletronicPointsPutRequest(eletronicPoints));
        }

        if (isPending(eletronicPoints)) {
            return applyPedingStatus(eletronicPoints);
        }

        int workRegime = eletronicPoints.getEmployeesRoles().getWorkRegime();
        if (workRegime == 1 || workRegime == 4) {
            registerStandardAndStraightShiftRegime(eletronicPoints, oldRegistersCurrent);
        }

        if (workRegime == 2 || workRegime == 3) {
            register24HoursAnd12HoursRegime(eletronicPoints, oldRegistersCurrent);
        }

        return replace(eletronicPoints.getId(), eletronicPointsMapper.eletronicPointsToEletronicPointsPutRequest(eletronicPoints));

    }

    private List<LocalTime> findCurrentRegisterPoints(EletronicPoints eletronicPoints) {
        List<LocalTime> currentRegisterPoints = new ArrayList<>();
        currentRegisterPoints.add(eletronicPoints.getRegister_1());
        currentRegisterPoints.add(eletronicPoints.getRegister_2());
        currentRegisterPoints.add(eletronicPoints.getRegister_3());
        currentRegisterPoints.add(eletronicPoints.getRegister_4());

        return currentRegisterPoints;
    }

    private void closeRegister(EletronicPoints eletronicPoints) {
        eletronicPoints.setEndDate(LocalDate.now());
        eletronicPoints.setStatus(2);
    }

    private boolean isPending(EletronicPoints eletronicPoints) {
        int workRegime = eletronicPoints.getEmployeesRoles().getWorkRegime();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDateAndFirstRegister = LocalDateTime.of(eletronicPoints.getStartDate(), eletronicPoints.getRegister_1());
        long durationBetweenDates = Duration.between(startDateAndFirstRegister, now).toHours();

        if (now.toLocalDate().isAfter(eletronicPoints.getStartDate()) && (workRegime == 1 || workRegime == 4)) {
            return true;
        }

        if (workRegime == 2 && durationBetweenDates < 12) {
            return true;
        }

        if (workRegime == 3 && durationBetweenDates < 24) {
            return true;
        }

        return false;
    }

    private EletronicPointsDTO applyPedingStatus(EletronicPoints eletronicPoints) {
        switch (eletronicPoints.getEmployeesRoles().getWorkRegime()) {
            case 1, 4 -> eletronicPoints.setStatus(3);

            case 2, 3 -> {
                eletronicPoints.setStatus(3);
                eletronicPoints.setRegister_2(LocalTime.now());
            }
        }

        eletronicPoints.setEndDate(LocalDate.now());
        return replace(eletronicPoints.getId(), eletronicPointsMapper.eletronicPointsToEletronicPointsPutRequest(eletronicPoints));
    }

    private void registerStandardAndStraightShiftRegime(EletronicPoints eletronicPoints, List<LocalTime> actualRegisters) {
        LocalTime now = LocalTime.now();

        if (actualRegisters.getFirst() == null) {
            eletronicPoints.setRegister_1(now);
        }

        if (actualRegisters.get(1) == null) {
            eletronicPoints.setRegister_2(now);
        }

        if (actualRegisters.get(2) == null) {
            eletronicPoints.setRegister_3(now);
        }

        if (actualRegisters.get(3) == null) {
            eletronicPoints.setRegister_4(now);
            closeRegister(eletronicPoints);
        }
    }

    private void register24HoursAnd12HoursRegime(EletronicPoints eletronicPoints, List<LocalTime> actualRegisters) {
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