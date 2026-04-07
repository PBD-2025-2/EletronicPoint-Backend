package pbd.ponto_eletronico.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import pbd.ponto_eletronico.enums.StatusType;

@Entity
@Table(name = "PointAdjustment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointAdjustmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "justification", nullable = false)
    private String justification;
    @Type(JsonType.class)
    @Column(name = "details", columnDefinition = "jsonb", nullable = false)
    private Object details;
    @Column(name="status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;
    @ManyToOne
    @JoinColumn(name = "eletronic_points_id", columnDefinition = "bigint",  referencedColumnName = "id")
    private EletronicPoints eletronicPoints;
}
