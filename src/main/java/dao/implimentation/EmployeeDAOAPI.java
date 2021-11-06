package dao.implimentation;

import dao.contracts.ConnectionBuilder;
import dao.contracts.EmployeeDAO;
import entyties.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOAPI implements EmployeeDAO {

    private final String SELECT_ALL = "SELECT * FROM employee";
    private final String SELECT_EMPLOYEE= "SELECT * FROM employee WHERE employee_id=?";
    private final String UPDATE_EMPLOYEE= "UPDATE employee SET " +
            "employee_name =?, employee_second_name=?, employee_email=?, employee_phone=? WHERE employee_id=?";
    private final String INSERT="INSERT INTO employee (employee_name, employee_second_name, employee_email, employee_phone) " +
            "VALUES (?,?,?,?) WHERE employee_id=?";
    private ConnectionBuilder connectionBuilder=ConnectionBuilderAPI.getInstance();

    public Connection getConnection() throws SQLException { // do not forget to close connection
        return connectionBuilder.getConnection();
    }

    @Override
    public List<Employee> getEmployeesList() {
        List<Employee> employeesList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Employee employee = new Employee();
                    employee.setEmployeeId(result.getInt("employee_id"));
                    employee.setName(result.getString("employee_name"));
                    employee.setSecondName(result.getString("employee_second_name"));
                    employee.setPhone(result.getString("phone"));
                    employee.setEmail(result.getString("employee_email"));
                    employeesList.add(employee);
                }

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return employeesList;
    }

    @Override
    public Integer addEmployee(Employee employee) {
        Integer result =-1;
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(INSERT,new String[]{"employee_id"});
                statement.setString(1,employee.getName());
                statement.setString(2,employee.getSecondName());
                statement.setString(3,employee.getEmail());
                statement.setString(4,employee.getPhone());
                statement.executeUpdate();
                ResultSet resultSet=statement.getGeneratedKeys();
                while (resultSet.next()){
                    result=resultSet.getInt(1);
                }

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return result; // return -1 if adding was unsuccessful , else return employee_id
    }


    @Override
    public void updateEmployee(Employee employee) {
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE);
                statement.setString(1,employee.getName());
                statement.setString(2,employee.getSecondName());
                statement.setString(3,employee.getEmail());
                statement.setString(4,employee.getPhone());
                statement.setInt(5,employee.getEmployeeId());
                statement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Employee getEmployeeById(int employee_id) {
        Employee employee=new Employee();
        try {
            Connection connection = getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE);
                statement.setInt(1,employee_id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    employee.setEmployeeId(result.getInt("employee_id"));
                    employee.setName(result.getString("employee_name"));
                    employee.setSecondName(result.getString("employee_second_name"));
                    employee.setEmail(result.getString("employee_email"));
                    employee.setPhone(result.getString("phone"));
                }

            }
            catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                connection.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return employee;
    }
}
