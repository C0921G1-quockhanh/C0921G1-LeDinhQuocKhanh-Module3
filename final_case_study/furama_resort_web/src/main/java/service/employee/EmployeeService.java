package service.employee;

import bean.Employee;
import repository.employee.EmployeeRepository;
import repository.employee.IEmployeeRepository;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeService {

    private IEmployeeRepository iEmployeeRepository = new EmployeeRepository();

    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        this.iEmployeeRepository.insertEmployee(employee);
    }

    @Override
    public Employee selectEmployee(int employeeID) {
        return this.iEmployeeRepository.selectEmployee(employeeID);
    }

    @Override
    public List<Employee> selectAllEmployees() {
        return this.iEmployeeRepository.selectAllEmployees();
    }

    @Override
    public boolean deleteEmployee(int employeeID) throws SQLException {
        return this.iEmployeeRepository.deleteEmployee(employeeID);
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        return this.iEmployeeRepository.updateEmployee(employee);
    }
}
