package pbd.ponto_eletronico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pbd.ponto_eletronico.entity.Roster;

import java.util.List;

public interface RosterRepository extends JpaRepository<Roster, Long> {
    Roster findByName(String name);

}
