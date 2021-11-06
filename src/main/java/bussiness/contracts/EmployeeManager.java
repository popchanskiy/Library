package bussiness.contracts;

import entyties.Employee;
import filter.EmployeeFilter;

import java.util.List;

public interface EmployeeManager {
    public void getEmployee(EmployeeFilter employeeFilter);
    public List<Employee> getEmployeesList();
    public Integer addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
}
