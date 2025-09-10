package fbd.ponto_eletronico.util;

import fbd.ponto_eletronico.entity.EmployeesRoles;

import java.util.List;

public interface FilterActivesRoles {
    static List<EmployeesRoles> filterActivesRoles(List<EmployeesRoles> employeesRoles){
        return employeesRoles.stream().filter(EmployeesRoles::getStatus).toList();
    }
}