package fbd.ponto_eletronico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Integer work_regime;
    @ManyToOne
    @JoinColumn(columnDefinition = "employee_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"cpf"})
    private Employee employee;
    @ManyToOne
    @JoinColumn(columnDefinition = "role_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"company"})
    private Role role;
    @ManyToOne
    @JoinColumn(columnDefinition = "companyId", referencedColumnName = "id")
    @JsonIgnoreProperties({"id", "cnpj"})
    private Company company;

}
