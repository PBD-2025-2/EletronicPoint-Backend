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
    private LocalDate startDate;
    private LocalTime register_1;
    private LocalTime register_2;
    private LocalTime register_3;
    private LocalTime register_4;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private OriginType origin;
    private Integer statusEletronicPoint;
    private Long employeesRolesId;
    @Enumerated(EnumType.STRING)
    private EletronicPointArchiveStatus statusArchive;
    private LocalDateTime processDate;
    private String fileBatch;
}