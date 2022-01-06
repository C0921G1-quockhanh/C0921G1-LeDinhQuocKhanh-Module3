package repository.employee;

import bean.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeRepository {
    public void insertEmployee(Employee employee) throws SQLException;

    public Employee selectEmployee(int employeeID);

    public List<Employee> selectAllEmployees();

    public boolean deleteEmployee(int employeeID) throws SQLException;

    public boolean updateEmployee(Employee employee) throws SQLException;

    public List<Employee> searchEmployeeByElement(String element);
}
