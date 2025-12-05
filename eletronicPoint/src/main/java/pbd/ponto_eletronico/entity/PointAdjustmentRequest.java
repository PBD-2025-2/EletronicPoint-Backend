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
    private String justification;
    @Type(JsonType.class)
    @Column(name = "details", columnDefinition = "jsonb")
    private Object details;
    @Enumerated(EnumType.STRING)
    private StatusType status;
    @ManyToOne
    @JoinColumn(columnDefinition = "eletronic_points_id",  referencedColumnName = "id")
    private EletronicPoints eletronicPoints;
}
