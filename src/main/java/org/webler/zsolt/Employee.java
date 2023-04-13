package org.webler.zsolt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private int id;
    private String name;
    private int salary;
    private Organization organization;


    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    public void increaseSalary(int amount) {
        salary += amount;
    }

}
