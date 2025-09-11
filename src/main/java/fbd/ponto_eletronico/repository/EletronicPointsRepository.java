package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.EletronicPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EletronicPointsRepository extends JpaRepository<EletronicPoints, Long>{
    List<EletronicPoints> findByEmployeesRoles_Employee_Cpf(String cpf);
    List<EletronicPoints> findByEmployeesRoles_Role_Company_Cnpj(String cnpj);
    List<EletronicPoints> findByStartDate(LocalDate localDate);
    List<EletronicPoints> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<EletronicPoints> findByEmployeesRoles_Id(Long id);
}

