package fbd.ponto_eletronico.dto;

import fbd.ponto_eletronico.entity.Company;
import fbd.ponto_eletronico.entity.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class EmployeeDTO {
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(columnDefinition = "ID_Company", referencedColumnName = "id")
    private Company company;
}
