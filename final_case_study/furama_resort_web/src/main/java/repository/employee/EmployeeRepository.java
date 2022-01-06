package repository.employee;

import bean.*;
import repository.BaseRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {
    private static final String INSERT_EMPLOYEE_SQL = "insert into employee(employee_name,date_of_birth,identity_number,salary,phone_number,email,address,position_id,qualification_id,department_id,username)\n" +
                                                    "values(?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SELECT_EMPLOYEE_BY_ID = "select E.employee_id,E.employee_name,E.date_of_birth,E.identity_number,E.salary,E.phone_number,E.email,E.address,P.position_id,P.position_name,Q.qualification_name,D.department_name,E.username\n" +
                                                    "from employee E\n" +
                                                    "join `position` P on P.position_id = E.position_id\n" +
                                                    "join qualification Q on Q.qualification_id = E.qualification_id\n" +
                                                    "join department D on D.department_id = E.department_id\n" +
                                                    "where E.employee_id = ?";

    private static final String SELECT_ALL_EMPLOYEES = "select E.employee_id,E.employee_name,E.date_of_birth,E.identity_number,E.salary,E.phone_number,E.email,E.address,P.position_name,Q.qualification_name,D.department_name,E.username\n" +
                                                    "from employee E\n" +
                                                    "join `position` P on P.position_id = E.position_id\n" +
                                                    "join qualification Q on Q.qualification_id = E.qualification_id\n" +
                                                    "join department D on D.department_id = E.department_id\n" +
                                                    "order by E.employee_id";

    private static final String DELETE_EMPLOYEE_SQL = "delete from employee\n" +
                                                    "where employee_id = ?";

    private static final String UPDATE_EMPLOYEE_SQL = "update employee\n" +
                                                    "set employee_name = ?, date_of_birth = ?, identity_number = ?, salary = ?, phone_number = ?, email = ?, address = ?, position_id = ?, qualification_id = ?, department_id = ?, username = ?\n" +
                                                    "where employee_id = ?";

    private static final String SEARCH_EMPLOYEE_BY_ELEMENT = "select E.employee_id,E.employee_name,E.date_of_birth,E.identity_number,E.salary,E.phone_number,E.email,E.address,P.position_name,Q.qualification_name,D.department_name,E.username\n" +
                                                    "from employee E\n" +
                                                    "join `position` P on P.position_id = E.position_id\n" +
                                                    "join qualification Q on Q.qualification_id = E.qualification_id\n" +
                                                    "join department D on D.department_id = E.department_id\n" +
                                                    "where E.employee_name like concat('%',?,'%')\n" +
                                                    "order by E.employee_id";

    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(INSERT_EMPLOYEE_SQL);

            preparedStatement.setString(1,employee.getEmployeeName());
            preparedStatement.setDate(2,employee.getDateOfBirth());
            preparedStatement.setString(3,employee.getIdentityNumber());
            preparedStatement.setDouble(4,employee.getSalary());
            preparedStatement.setString(5,employee.getPhoneNumber());
            preparedStatement.setString(6,employee.getEmail());
            preparedStatement.setString(7,employee.getAddress());
            preparedStatement.setInt(8,employee.getPosition().getPositionID());
            preparedStatement.setInt(9,employee.getQualification().getQualificationID());
            preparedStatement.setInt(10,employee.getDepartment().getDepartmentID());
            preparedStatement.setString(11,employee.getUser().getUserName());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Employee selectEmployee(int employeeID) {
        Employee employee = null;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1,employeeID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String employeeName = resultSet.getString("employee_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String identityNumber = resultSet.getString("identity_number");
                double salary = resultSet.getDouble("salary");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                int positionID = resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");
                Position position = new Position(positionID,positionName);

                String qualificationName = resultSet.getString("qualification_name");
                Qualification qualification = new Qualification(qualificationName);

                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentName);

                String userName = resultSet.getString("username");
                User user = new User(userName);

                employee = new Employee(employeeID,employeeName,dateOfBirth,identityNumber,salary,phoneNumber,email,address,position,qualification,department,user);
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return employee;
    }

    @Override
    public List<Employee> selectAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_ALL_EMPLOYEES);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int employeeID = resultSet.getInt("employee_id");

                String employeeName = resultSet.getString("employee_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String identityNumber = resultSet.getString("identity_number");
                double salary = resultSet.getDouble("salary");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                String positionName = resultSet.getString("position_name");
                Position position = new Position(positionName);

                String qualificationName = resultSet.getString("qualification_name");
                Qualification qualification = new Qualification(qualificationName);

                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentName);

                String userName = resultSet.getString("username");
                User user = new User(userName);

                employees.add(new Employee(employeeID,employeeName,dateOfBirth,identityNumber,salary,phoneNumber,email,address,position,qualification,department,user));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return employees;
    }

    @Override
    public List<Employee> searchEmployeeByElement(String element) {
        List<Employee> employees = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SEARCH_EMPLOYEE_BY_ELEMENT);
            preparedStatement.setString(1,element);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int employeeID = resultSet.getInt("employee_id");

                String employeeName = resultSet.getString("employee_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String identityNumber = resultSet.getString("identity_number");
                double salary = resultSet.getDouble("salary");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                String positionName = resultSet.getString("position_name");
                Position position = new Position(positionName);

                String qualificationName = resultSet.getString("qualification_name");
                Qualification qualification = new Qualification(qualificationName);

                String departmentName = resultSet.getString("department_name");
                Department department = new Department(departmentName);

                String userName = resultSet.getString("username");
                User user = new User(userName);

                employees.add(new Employee(employeeID,employeeName,dateOfBirth,identityNumber,salary,phoneNumber,email,address,position,qualification,department,user));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return employees;
    }

    @Override
    public boolean deleteEmployee(int employeeID) throws SQLException {
        boolean rowDeleted = false;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(DELETE_EMPLOYEE_SQL);
            preparedStatement.setInt(1,employeeID);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return rowDeleted;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated = false;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(UPDATE_EMPLOYEE_SQL);

            preparedStatement.setString(1,employee.getEmployeeName());
            preparedStatement.setDate(2,employee.getDateOfBirth());
            preparedStatement.setString(3,employee.getIdentityNumber());
            preparedStatement.setDouble(4,employee.getSalary());
            preparedStatement.setString(5,employee.getPhoneNumber());
            preparedStatement.setString(6,employee.getEmail());
            preparedStatement.setString(7,employee.getAddress());
            preparedStatement.setInt(8,employee.getPosition().getPositionID());
            preparedStatement.setInt(9,employee.getQualification().getQualificationID());
            preparedStatement.setInt(10,employee.getDepartment().getDepartmentID());
            preparedStatement.setString(11,employee.getUser().getUserName());
            preparedStatement.setInt(12,employee.getEmployeeID());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
