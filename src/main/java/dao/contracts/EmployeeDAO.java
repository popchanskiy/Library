package dao.contracts;

import entyties.Employee;

import java.util.List;

public interface EmployeeDAO {
    //public void getEmployee(EmployeeFilter employeeFilter); TODO add filter class
    public List<Employee> getEmployeesList();
    public Integer addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public Employee getEmployeeById(int employee_id);


}
