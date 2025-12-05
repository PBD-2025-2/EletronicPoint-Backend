package pbd.ponto_eletronico.repository;

import pbd.ponto_eletronico.entity.EletronicPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.enums.OriginType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EletronicPointsRepository extends JpaRepository<EletronicPoints, Long>{
    List<EletronicPoints> findByEmployeesRoles_Employee_Cpf(String cpf);
    List<EletronicPoints> findByStartDate(LocalDate localDate);
    List<EletronicPoints> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<EletronicPoints> findByEmployeesRoles_IdOrderByIdAsc(Long id);
    List<EletronicPoints> findByEmployeesRoles_Role_Sectors_Name(String name);
    List<EletronicPoints> findByEmployeesRoles_Role_Sectors_Company_Cnpj(String cnpj);

    List<EletronicPoints> findByOrigin(OriginType origin);

    List<EletronicPoints> findByStatus(Integer status);
}

