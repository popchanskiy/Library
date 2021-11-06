package bussiness.implimentation;

import bussiness.contracts.EmployeeManager;
import dao.contracts.EmployeeDAO;
import entyties.Employee;
import filter.EmployeeFilter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class EmployeeManagerAPI implements EmployeeManager {
    private EmployeeDAO dao;

    public void setDao(EmployeeDAO dao) {
        this.dao = dao;
    }

    @Override
    public void getEmployee(EmployeeFilter employeeFilter) {

    }

    @Override
    public List<Employee> getEmployeesList() {
        return dao.getEmployeesList();
    }

    @Override
    public Integer addEmployee(Employee employee) {
        return dao.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        dao.updateEmployee(employee);

    }
}
