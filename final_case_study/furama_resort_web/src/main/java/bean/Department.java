package bean;

public class Department {
    protected int departmentID;
    protected String departmentName;

    public Department() {
    }

    public Department(int departmentID) {
        this.departmentID = departmentID;
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(int departmentID, String departmentName) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
