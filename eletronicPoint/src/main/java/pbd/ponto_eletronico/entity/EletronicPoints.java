package pbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pbd.ponto_eletronico.enums.OriginType;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "eletronic_points")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EletronicPoints {

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
    @Column(name = "end_date", columnDefinition = "date")
    private LocalDate endDate;
    @Column(name = "status", columnDefinition = "interger", nullable = false)
    private Integer status;
    @Column(name = "origin", length= 9, nullable = false)
    @Enumerated(EnumType.STRING)
    private OriginType origin;
    @ManyToOne
    @JoinColumn(name="employees_roles_id", columnDefinition = "bigint", referencedColumnName = "id")
    private EmployeesRoles employeesRoles;
}
