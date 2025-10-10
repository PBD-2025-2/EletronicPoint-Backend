package pbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees_roles")
public class EmployeesRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status;
    @ManyToOne
    @JoinColumn(columnDefinition = "roster_id", referencedColumnName = "id")
    private Roster roster;
    @ManyToOne
    @JoinColumn(columnDefinition = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(columnDefinition = "role_id", referencedColumnName = "id")
    private Role role;
}
