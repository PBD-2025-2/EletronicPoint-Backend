package fbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDate startDate;
    private LocalTime register_1;
    private LocalTime register_2;
    private LocalTime register_3;
    private LocalTime register_4;
    private LocalDate endDate;
    private Integer status;
    @ManyToOne
    @JoinColumn(columnDefinition = "employee_roles_id", referencedColumnName = "id")
    private EmployeesRoles employeesRoles;
}
