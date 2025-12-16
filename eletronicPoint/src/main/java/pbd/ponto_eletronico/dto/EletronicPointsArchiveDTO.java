package pbd.ponto_eletronico.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import pbd.ponto_eletronico.enums.EletronicPointArchiveStatus;
import pbd.ponto_eletronico.enums.OriginType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EletronicPointsArchiveDTO(
        LocalDate startDate,
        LocalTime register_1,
        LocalTime register_2,
        LocalTime register_3,
        LocalTime register_4,
        LocalDate endDate,
        OriginType origin,
        Integer statusEletronicPoint,
        Long employeesRolesId,
        EletronicPointArchiveStatus statusArchive,
        LocalDateTime processDate,
        String fileBatch)  {

    public EletronicPointsArchiveDTO(LocalDate startDate, LocalTime register_1, LocalTime register_2, LocalTime register_3, LocalDate endDate, OriginType origin, Integer statusEletronicPoint, Long employeesRolesId, EletronicPointArchiveStatus statusArchive, LocalDateTime processDate, String fileBatch) {
        this(startDate, register_1, register_2, register_3, null, endDate, origin, statusEletronicPoint, employeesRolesId, statusArchive, processDate, fileBatch);
    }

    public EletronicPointsArchiveDTO(LocalDate startDate, LocalTime register_1, LocalTime register_2, LocalDate endDate, OriginType origin, Integer statusEletronicPoint, Long employeesRolesId, EletronicPointArchiveStatus statusArchive, LocalDateTime processDate, String fileBatch) {
        this(startDate, register_1, register_2, null, null, endDate, origin, statusEletronicPoint, employeesRolesId, statusArchive, processDate, fileBatch);
    }

    public EletronicPointsArchiveDTO(LocalDate startDate, LocalTime register_1, LocalDate endDate, OriginType origin, Integer statusEletronicPoint, Long employeesRolesId, EletronicPointArchiveStatus statusArchive, LocalDateTime processDate, String fileBatch) {
        this(startDate, register_1, null, null, null, endDate, origin, statusEletronicPoint, employeesRolesId, statusArchive, processDate, fileBatch);
    }

    @Override
    public LocalTime register_1() {
        return register_1;
    }

    @Override
    public LocalDate startDate() {
        return startDate;
    }

    @Override
    public LocalTime register_2() {
        return register_2;
    }

    @Override
    public LocalTime register_3() {
        return register_3;
    }

    @Override
    public LocalTime register_4() {
        return register_4;
    }

    @Override
    public LocalDate endDate() {
        return endDate;
    }

    @Override
    public OriginType origin() {
        return origin;
    }

    @Override
    public Integer statusEletronicPoint() {
        return statusEletronicPoint;
    }

    @Override
    public Long employeesRolesId() {
        return employeesRolesId;
    }

    @Override
    public EletronicPointArchiveStatus statusArchive() {
        return statusArchive;
    }

    @Override
    public LocalDateTime processDate() {
        return processDate;
    }

    @Override
    public String fileBatch() {
        return fileBatch;
    }
}