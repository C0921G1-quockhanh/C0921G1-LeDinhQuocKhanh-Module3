package bean;

import java.sql.Date;

public class Customer {
    protected int customerID;
    protected CustomerType customerType;
    protected String customerName;
    protected Date dateOfBirth;
    protected boolean sex;
    protected String identityNumber;
    protected String phoneNumber;
    protected String email;
    protected String address;

    public Customer() {
    }

    public Customer(CustomerType customerType, String customerName, Date dateOfBirth, boolean sex, String identityNumber, String phoneNumber, String email, String address) {
        this.customerType = customerType;
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.identityNumber = identityNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Customer(int customerID, CustomerType customerType, String customerName, Date dateOfBirth, boolean sex, String identityNumber, String phoneNumber, String email, String address) {
        this.customerID = customerID;
        this.customerType = customerType;
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.identityNumber = identityNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
