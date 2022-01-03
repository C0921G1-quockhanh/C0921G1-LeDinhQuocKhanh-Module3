package service.customer;

import bean.Customer;
import repository.customer.CustomerRepository;
import repository.customer.ICustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements ICustomerService {

    private ICustomerRepository iCustomerRepository = new CustomerRepository();

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        this.iCustomerRepository.insertCustomer(customer);
    }

    @Override
    public Customer selectCustomer(int customerID) {
        return this.iCustomerRepository.selectCustomer(customerID);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return this.iCustomerRepository.selectAllCustomers();
    }

    @Override
    public boolean deleteCustomer(int customerID) throws SQLException {
        return this.iCustomerRepository.deleteCustomer(customerID);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        return this.iCustomerRepository.updateCustomer(customer);
    }
}
