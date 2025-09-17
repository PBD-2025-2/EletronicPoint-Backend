package fbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employeesCompanies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesCompanies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(columnDefinition = "company_id", referencedColumnName = "id")
    private Company company;
    @ManyToOne
    @JoinColumn(columnDefinition = "employee_id", referencedColumnName = "id")
    private Employee employee;
}