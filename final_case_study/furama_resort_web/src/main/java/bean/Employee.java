package bean;

import java.sql.Date;

public class Employee {
    protected int employeeID;
    protected String employeeName;
    protected Date dateOfBirth;
    protected String identityNumber;
    protected double salary;
    protected String phoneNumber;
    protected String email;
    protected String address;
    protected Position position;
    protected Qualification qualification;
    protected Department department;
    protected User user;

    public Employee() {
    }

    public Employee(int employeeID) {
        this.employeeID = employeeID;
    }

    public Employee(String employeeName, Date dateOfBirth, String identityNumber, double salary, String phoneNumber, String email, String address, Position position, Qualification qualification, Department department, User user) {
        this.employeeName = employeeName;
        this.dateOfBirth = dateOfBirth;
        this.identityNumber = identityNumber;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.position = position;
        this.qualification = qualification;
        this.department = department;
        this.user = user;
    }

    public Employee(int employeeID, String employeeName, Date dateOfBirth, String identityNumber, double salary, String phoneNumber, String email, String address, Position position, Qualification qualification, Department department, User user) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.dateOfBirth = dateOfBirth;
        this.identityNumber = identityNumber;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.position = position;
        this.qualification = qualification;
        this.department = department;
        this.user = user;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
