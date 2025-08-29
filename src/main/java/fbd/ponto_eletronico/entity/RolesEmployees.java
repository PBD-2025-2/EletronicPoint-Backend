package fbd.ponto_eletronico.entity;

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
@Table(name = "roles_employees")
public class RolesEmployees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean status;
    private Integer work_regime;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Role role;
}
