package fbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "eletronic_points")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EletronicPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Time startDate;
    private Time register_1;
    private Time register_2;
    private Time register_3;
    private Time register_4;
    private LocalDate endDate;
    private Integer status;
    @ManyToOne
    @JoinColumn(columnDefinition = "employee_roles_id", referencedColumnName = "id")
    private EmployeesRoles employeeRoles;



}
