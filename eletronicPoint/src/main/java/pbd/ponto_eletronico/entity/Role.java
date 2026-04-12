package pbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "roles")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "responsibility", nullable = false, length = 255)
    private String responsibility;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;

    @ManyToOne
    @JoinColumn(columnDefinition = "sectors_id", referencedColumnName = "id")
    private Sectors sectors;
}
