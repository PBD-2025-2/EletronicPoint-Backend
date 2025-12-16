package pbd.ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbd.ponto_eletronico.entity.EletronicPointsArchive;
import pbd.ponto_eletronico.enums.EletronicPointArchiveStatus;

import java.util.List;

public interface EletronicPoinstArchiverRepository extends JpaRepository<EletronicPointsArchive, Long> {
    List<EletronicPointsArchive> findByFileBatch(String fileBatch);
    List<EletronicPointsArchive> findByStatusArchive(EletronicPointArchiveStatus fileBatch);
}
