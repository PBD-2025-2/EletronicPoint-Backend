package pbd.ponto_eletronico.util;

import pbd.ponto_eletronico.entity.EmployeesRoles;

import java.util.List;

public interface FilterActivesRoles {
    static List<EmployeesRoles> filterActivesRoles(List<EmployeesRoles> employeesRoles){
        return employeesRoles.stream().filter(EmployeesRoles::getStatus).toList();
    }
}