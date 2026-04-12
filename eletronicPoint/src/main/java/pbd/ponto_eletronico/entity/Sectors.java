package pbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sectors")
public class Sectors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sigla", length = 5)
    private String sigla;

    @ManyToOne
    @JoinColumn(columnDefinition = "company_id", referencedColumnName = "id")
    private Company company;
}
