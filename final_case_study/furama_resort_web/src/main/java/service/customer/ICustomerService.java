package service.customer;

import bean.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
    public void insertCustomer(Customer customer) throws SQLException;

    public Customer selectCustomer(int customerID);

    public List<Customer> selectAllCustomers();

    public boolean deleteCustomer(int customerID) throws SQLException;

    public boolean updateCustomer(Customer customer) throws SQLException;
}
