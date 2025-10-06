package pbd.ponto_eletronico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private TypeRoster type;
    @Column(columnDefinition = "weekly_workload")
    private String weeklyWorkload;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String schedules;

}
