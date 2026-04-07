package pbd.ponto_eletronico.entity;

import com.vladmihalcea.hibernate.type.json.JsonBlobType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import pbd.ponto_eletronico.enums.GenderType;
import pbd.ponto_eletronico.enums.RosterType;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length= 50, nullable = false)
    private String name;
    @Column(name = "cpf", length= 11, unique = true, nullable = false)
    private String cpf;
    @Column(name="email", length = 100, nullable = false)
    private String email;
    @Column(name="phone_number", length = 12, nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "text", length = 9, nullable = false)
    private GenderType gender;
    @Type(JsonType.class)
    @Column(name = "address", columnDefinition = "jsonb", nullable = false)
    private Object address;
}
