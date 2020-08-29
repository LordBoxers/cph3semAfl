/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author Mibsen
 */
public class EmployeeDTO {
    private long ID;
    private String name;
    private String adress;

    public EmployeeDTO(Employee e) {
        this.ID = e.getId();
        this.name = e.getName();
        this.adress = e.getAddress();
    }
    
    
}
