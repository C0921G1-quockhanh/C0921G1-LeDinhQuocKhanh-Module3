package repository.customer;

import bean.Customer;
import bean.CustomerType;
import repository.BaseRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private static final String INSERT_CUSTOMER_SQL = "insert into customer(customer_type_id,customer_name,date_of_birth,sex,identity_number,phone_number,email,address)\n"
                                                        + "values(?,?,?,?,?,?,?,?)";

    private static final String SELECT_CUSTOMER_BY_ID = "select C.customer_id,CT.customer_type_id, CT.customer_type_name, C.customer_name, C.date_of_birth, C.sex, C.identity_number, C.phone_number, C.email, C.address from customer C\n" +
                                                    "join customer_type CT on C.customer_type_id = CT.customer_type_id\n" +
                                                    "where C.customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS = "select C.customer_id,CT.customer_type_id, CT.customer_type_name, C.customer_name, C.date_of_birth, C.sex, C.identity_number, C.phone_number, C.email, C.address from customer C\n" +
                                                    "join customer_type CT on C.customer_type_id = CT.customer_type_id\n" +
                                                    "order by C.customer_id";

    private static final String DELETE_CUSTOMER_SQL = "delete from customer\n" + "where customer_id = ?";

    private static final String UPDATE_CUSTOMER_SQL = "update customer\n" +
            "set customer_type_id = ?, customer_name = ?, date_of_birth = ?, sex = ?, identity_number = ?, phone_number = ?, email = ?, address = ?\n" +
            "where customer_id = ?";

    private static final String SEARCH_CUSTOMER_BY_ELEMENT = "select C.customer_id,CT.customer_type_id, CT.customer_type_name, C.customer_name, C.date_of_birth, C.sex, C.identity_number, C.phone_number, C.email, C.address\n" +
                                                    "from customer C\n" +
                                                    "join customer_type CT on C.customer_type_id = CT.customer_type_id\n" +
                                                    "where C.customer_name like concat('%',?,'%')\n" +
                                                    "order by C.customer_id";

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(INSERT_CUSTOMER_SQL);
            preparedStatement.setInt(1,customer.getCustomerType().getCustomerTypeID());
            preparedStatement.setString(2,customer.getCustomerName());
            preparedStatement.setDate(3,customer.getDateOfBirth());
            preparedStatement.setBoolean(4,customer.isSex());
            preparedStatement.setString(5,customer.getIdentityNumber());
            preparedStatement.setString(6,customer.getPhoneNumber());
            preparedStatement.setString(7,customer.getEmail());
            preparedStatement.setString(8,customer.getAddress());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Customer selectCustomer(int customerID) {
        Customer customer = null;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1,customerID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int customerTypeID = resultSet.getInt("customer_type_id");
                String customerTypeName = resultSet.getString("customer_type_name");

                CustomerType customerType = new CustomerType(customerTypeID,customerTypeName);

                String customerName = resultSet.getString("customer_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                Boolean sex = resultSet.getBoolean("sex");
                String identityNumber = resultSet.getString("identity_number");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                customer = new Customer(customerID,customerType,customerName,dateOfBirth,sex,identityNumber,phoneNumber,email,address);
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return customer;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_ALL_CUSTOMERS);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customer_id");

                int customerTypeID = resultSet.getInt("customer_type_id");
                String customerTypeName = resultSet.getString("customer_type_name");

                CustomerType customerType = new CustomerType(customerTypeID,customerTypeName);

                String customerName = resultSet.getString("customer_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                Boolean sex = resultSet.getBoolean("sex");
                String identityNumber = resultSet.getString("identity_number");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                customers.add(new Customer(customerID,customerType,customerName,dateOfBirth,sex,identityNumber,phoneNumber,email,address));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return customers;
    }

    @Override
    public List<Customer> searchCustomerByElement(String element) {
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SEARCH_CUSTOMER_BY_ELEMENT);
            preparedStatement.setString(1,element);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customer_id");

                int customerTypeID = resultSet.getInt("customer_type_id");
                String customerTypeName = resultSet.getString("customer_type_name");

                CustomerType customerType = new CustomerType(customerTypeID,customerTypeName);

                String customerName = resultSet.getString("customer_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                Boolean sex = resultSet.getBoolean("sex");
                String identityNumber = resultSet.getString("identity_number");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                customers.add(new Customer(customerID,customerType,customerName,dateOfBirth,sex,identityNumber,phoneNumber,email,address));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return customers;
    }

    @Override
    public boolean deleteCustomer(int customerID) throws SQLException {
        boolean rowDeleted = false;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(DELETE_CUSTOMER_SQL);
            preparedStatement.setInt(1,customerID);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return rowDeleted;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean rowUpdated = false;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(UPDATE_CUSTOMER_SQL);

            preparedStatement.setInt(1,customer.getCustomerType().getCustomerTypeID());
            preparedStatement.setString(2,customer.getCustomerName());
            preparedStatement.setDate(3,customer.getDateOfBirth());
            preparedStatement.setBoolean(4,customer.isSex());
            preparedStatement.setString(5,customer.getIdentityNumber());
            preparedStatement.setString(6,customer.getPhoneNumber());
            preparedStatement.setString(7,customer.getEmail());
            preparedStatement.setString(8,customer.getAddress());
            preparedStatement.setInt(9,customer.getCustomerID());

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
