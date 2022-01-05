package service.employee;

import bean.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeService {
    public void insertEmployee(Employee employee) throws SQLException;

    public Employee selectEmployee(int employeeID);

    public List<Employee> selectAllEmployees();

    public boolean deleteEmployee(int employeeID) throws SQLException;

    public boolean updateEmployee(Employee employee) throws SQLException;
}
