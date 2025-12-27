package pbd.ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.entity.PointAdjustmentRequest;
import pbd.ponto_eletronico.enums.StatusType;

import java.util.List;

public interface PointAdjustmentRequestRepository extends JpaRepository<PointAdjustmentRequest, Long> {
    List<PointAdjustmentRequest> findByStatus(StatusType status);

    List<PointAdjustmentRequest> findByEletronicPoints_Id(Long id);
}
