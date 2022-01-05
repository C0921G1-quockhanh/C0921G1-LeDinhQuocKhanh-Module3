package bean;

import java.sql.Date;

public class Contract {
    protected int contractID;
    protected Date startDate;
    protected Date endDate;
    protected double deposit;
    protected Employee employee;
    protected Customer customer;
    protected Service service;

    public Contract() {
    }

    public Contract(int contractID) {
        this.contractID = contractID;
    }

    public Contract(Date startDate, Date endDate, double deposit, Employee employee, Customer customer, Service service) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.employee = employee;
        this.customer = customer;
        this.service = service;
    }

    public Contract(int contractID, Date startDate, Date endDate, double deposit, Employee employee, Customer customer, Service service) {
        this.contractID = contractID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
        this.employee = employee;
        this.customer = customer;
        this.service = service;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}

