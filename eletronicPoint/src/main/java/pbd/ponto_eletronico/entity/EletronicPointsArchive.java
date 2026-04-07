package pbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pbd.ponto_eletronico.enums.EletronicPointArchiveStatus;
import pbd.ponto_eletronico.enums.OriginType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "eletronic_points_archive")
public class EletronicPointsArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", columnDefinition = "date", nullable = false)
    private LocalDate startDate;
    @Column(name = "register_1", columnDefinition = "time without time zone", nullable = false)
    private LocalTime register_1;
    @Column(name = "register_2", columnDefinition = "time without time zone")
    private LocalTime register_2;
    @Column(name = "register_3", columnDefinition = "time without time zone")
    private LocalTime register_3;
    @Column(name = "register_4", columnDefinition = "time without time zone")
    private LocalTime register_4;
    @Column(name = "end_date", columnDefinition = "date", nullable = false)
    private LocalDate endDate;
    @Column(name = "origin", length= 9, nullable = false)
    @Enumerated(EnumType.STRING)
    private OriginType origin;
    @Column(name= "status_eletronic_point", columnDefinition = "integer", nullable = false)
    private Integer statusEletronicPoint;
    @Column(name= "employee_role_id", columnDefinition = "bigint", nullable = false)
    private Long employeesRolesId;
    @Column(name = "status_archive", length= 13, nullable = false)
    @Enumerated(EnumType.STRING)
    private EletronicPointArchiveStatus statusArchive;
    @Column(name = "process_date", columnDefinition = "timestamp without time zone", nullable = false)
    private LocalDateTime processDate;
    @Column(name = "file_batch", nullable = false)
    private String fileBatch;
}