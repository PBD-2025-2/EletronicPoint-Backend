package fbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cpf;
//    @ManyToOne
//    @JoinColumn(columnDefinition = "ID_Company", referencedColumnName = "id")
//    private Company company;

//    private String company_name = company.getName();

}
