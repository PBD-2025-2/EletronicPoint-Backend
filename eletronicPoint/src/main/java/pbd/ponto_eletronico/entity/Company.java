package pbd.ponto_eletronico.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 25, nullable = false)
    private String name;
    @Column(name = "cnpj", length = 14, nullable = false)
    private String cnpj;
    @Column(name = "email", length = 25, nullable = false)
    private String email;
    @Type(JsonType.class)
    @Column(name = "address", columnDefinition = "jsonb", nullable = false)
    private Object address;
    @Column(name = "phone_number", length = 12, nullable = false)
    private String phoneNumber;

}
