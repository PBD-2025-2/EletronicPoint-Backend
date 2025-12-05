package pbd.ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.entity.PointAdjustmentRequest;
import pbd.ponto_eletronico.enums.StatusType;

import java.util.Optional;

public interface PointAdjustmentRequestRepository extends JpaRepository<PointAdjustmentRequest, Long> {
    Optional<PointAdjustmentRequest> findByStatus(StatusType status);
}
