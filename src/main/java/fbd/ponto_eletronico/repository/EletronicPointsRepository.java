package fbd.ponto_eletronico.repository;

import fbd.ponto_eletronico.entity.EletronicPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EletronicPointsRepository extends JpaRepository<EletronicPoints, Long>{
}
