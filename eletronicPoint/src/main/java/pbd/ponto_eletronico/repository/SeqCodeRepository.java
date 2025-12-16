package pbd.ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pbd.ponto_eletronico.entity.EletronicPointsArchive;

@Repository
public interface SeqCodeRepository extends JpaRepository<EletronicPointsArchive, Long> {
    @Query(value = "SELECT nextval('seq_code')", nativeQuery = true)
    long nextSeqCode();
}
