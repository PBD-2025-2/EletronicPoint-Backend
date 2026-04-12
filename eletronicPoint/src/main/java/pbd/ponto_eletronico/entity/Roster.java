package pbd.ponto_eletronico.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pbd.ponto_eletronico.enums.RosterType;

import java.time.LocalTime;

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
    @Column(name = "name", length = 25, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 7, nullable = false)
    private RosterType type;
    @Column(name = "weekly_workload", nullable = false)
    private int weeklyWorkload;
    @Type(JsonType.class)
    @Column(name = "schedules", columnDefinition = "jsonb", nullable = false)
    private Object schedules;
    @Column(name = "daily_workload_limit", nullable = false, columnDefinition = "TIME")
    private LocalTime dailyWorkloadLimit;
    @Column(name = "work_pattern", length = 6, nullable = false)
    private String workPattern;


}
