package service;

import bean.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    Customer findByID(Integer id);
    void update(Integer id, Customer customer);
    void remove(Integer id);
}
