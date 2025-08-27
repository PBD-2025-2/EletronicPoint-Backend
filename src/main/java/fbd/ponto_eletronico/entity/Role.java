package fbd.ponto_eletronico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "roles")
@Entity
public class Role {

    @Id
    private Long id;

}
