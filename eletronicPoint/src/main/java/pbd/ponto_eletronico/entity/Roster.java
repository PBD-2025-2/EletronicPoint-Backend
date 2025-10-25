package pbd.ponto_eletronico.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pbd.ponto_eletronico.enums.TypeRoster;

@Getter
@Setter
@Entity
@Table(name = "rosters")
@AllArgsConstructor
@NoArgsConstructor
public class Roster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeRoster type;
    @Column(columnDefinition = "weekly_workload")
    private int weeklyWorkload;
    @Type(JsonType.class)
    @Column(name = "schedules", columnDefinition = "jsonb")
    private Object schedules;

}
