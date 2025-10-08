package pbd.ponto_eletronico.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pbd.ponto_eletronico.enums.TypeRoster;

import java.util.List;

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
    private String weeklyWorkload;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Schedule> schedules;
//    @Column(columnDefinition = "jsonb")
//    private DutySchedules dutySchedules;
}
