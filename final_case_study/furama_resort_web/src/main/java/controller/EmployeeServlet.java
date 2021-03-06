package controller;

import bean.*;
import service.employee.EmployeeService;
import service.employee.IEmployeeService;
import validation.Validate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "EmployeeServlet",urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {

    private IEmployeeService iEmployeeService = new EmployeeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        try {
            switch (action) {
                case "create":
                    insertEmployee(request,response);
                    break;

                case "edit":
                    updateEmployee(request,response);
                    break;

                case "search":
                    searchEmployee(request,response);
                    break;
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        try {
            switch (action) {
                case "create":
                    showNewForm(request,response);
                    break;

                case "edit":
                    showEditForm(request,response);
                    break;

                case "delete":
                    deleteEmployee(request,response);
                    break;

                default:
                    listEmployee(request,response);
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void searchEmployee(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        String element = request.getParameter("search");
        List<Employee> employees = this.iEmployeeService.searchEmployeeByElement(element);

        request.setAttribute("employees",employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/search.jsp");
        dispatcher.forward(request,response);
    }

    private void listEmployee(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Employee> employees = this.iEmployeeService.selectAllEmployees();
        request.setAttribute("employees",employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/create.jsp");
        dispatcher.forward(request,response);
    }

    private void insertEmployee(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
       try {
           boolean warningSign = false;

           String employeeName = request.getParameter("employeeName");

           String dateString = request.getParameter("dateOfBirth");
           request.setAttribute("dateString",dateString);
           Date dateOfBirth = null;

           if (Validate.regexDateOfBirth(dateString)) {
               request.setAttribute("birthdayWarningMsg","Date of Birth is invalid!");
               warningSign = true;
           } else {
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               java.util.Date parsed = format.parse(dateString);
               dateOfBirth = new Date(parsed.getTime());
           }


           String identityNumber = request.getParameter("identityNumber");
           if (!Validate.regexIdentityNumber(identityNumber)) {
               request.setAttribute("identityNumberWarningMsg","Identity number is invalid!");
               warningSign = true;
           }


           double salary = Double.parseDouble(request.getParameter("salary"));
           if (!Validate.isPositive(salary)) {
               request.setAttribute("salaryWarningMsg","Salary is invalid!");
               warningSign = true;
           }

           String phoneNumber = request.getParameter("phoneNumber");
           if (!Validate.regexPhoneNumber(phoneNumber)) {
               request.setAttribute("phoneNumberWarningMsg","Phone number is invalid!");
               warningSign = true;
           }

           String email = request.getParameter("email");
           if (!Validate.regexEmail(email)) {
               request.setAttribute("emailWarningMsg","Email is invalid!");
               warningSign = true;
           }

           String address = request.getParameter("address");

           int positionID = Integer.parseInt(request.getParameter("positionID"));
           Position position = new Position(positionID);

           int qualificationID = Integer.parseInt(request.getParameter("qualificationID"));
           Qualification qualification = new Qualification(qualificationID);

           int departmentID = Integer.parseInt(request.getParameter("departmentID"));
           Department department = new Department(departmentID);

           String userName = request.getParameter("username");
           User user = new User(userName);

           Employee newEmployee = new Employee(employeeName,dateOfBirth,identityNumber,salary,phoneNumber,email,address,position,qualification,department,user);

           if (warningSign) {
               request.setAttribute("employee",newEmployee);
               showNewForm(request,response);
           } else {
               this.iEmployeeService.insertEmployee(newEmployee);
               response.sendRedirect("/employees");
           }
       }
       catch (ParseException e) {
           e.printStackTrace();
       }
    }

    private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int employeeID = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = this.iEmployeeService.selectEmployee(employeeID);

        request.setAttribute("existingEmployee",existingEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void updateEmployee(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));

            String employeeName = request.getParameter("employeeName");

            String dateString = request.getParameter("dateOfBirth");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(dateString);
            Date dateOfBirth = new Date(parsed.getTime());

            String identityNumber = request.getParameter("identityNumber");
            double salary = Double.parseDouble(request.getParameter("salary"));
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            int positionID = Integer.parseInt(request.getParameter("positionID"));
            Position position = new Position(positionID);

            int qualificationID = Integer.parseInt(request.getParameter("qualificationID"));
            Qualification qualification = new Qualification(qualificationID);

            int departmentID = Integer.parseInt(request.getParameter("departmentID"));
            Department department = new Department(departmentID);

            String userName = request.getParameter("username");
            User user = new User(userName);

            Employee updatedEmployee = new Employee(employeeID,employeeName,dateOfBirth,identityNumber,salary,phoneNumber,email,address,position,qualification,department,user);
            this.iEmployeeService.updateEmployee(updatedEmployee);

            RequestDispatcher dispatcher = request.getRequestDispatcher("employee/edit.jsp");
            dispatcher.forward(request,response);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void deleteEmployee(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int employeeID = Integer.parseInt(request.getParameter("id"));
        this.iEmployeeService.deleteEmployee(employeeID);

        List<Employee> employees = this.iEmployeeService.selectAllEmployees();
        request.setAttribute("employees",employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee/list.jsp");
        dispatcher.forward(request,response);
    }
}
