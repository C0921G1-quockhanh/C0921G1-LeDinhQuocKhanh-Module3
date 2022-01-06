package controller;

import bean.Customer;
import bean.CustomerType;
import service.customer.CustomerService;
import service.customer.ICustomerService;
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

@WebServlet(name = "CustomerServlet",urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {

    private ICustomerService iCustomerService = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        try {
            switch (action) {
                case "create":
                    insertCustomer(request,response);
                    break;

                case "edit":
                    updateCustomer(request,response);
                    break;

                case "search":
                    searchCustomer(request,response);
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
                    deleteCustomer(request,response);
                    break;

                default:
                    listCustomer(request,response);
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void searchCustomer(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        String element = request.getParameter("search");
        List<Customer> customers = this.iCustomerService.searchCustomerByElement(element);

        request.setAttribute("customers",customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/search.jsp");
        dispatcher.forward(request,response);
    }

    private void listCustomer(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Customer> customers = this.iCustomerService.selectAllCustomers();
        request.setAttribute("customers",customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(request,response);
    }

    private void insertCustomer(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            boolean warningSign = false;

            int customerTypeID = Integer.parseInt(request.getParameter("customerTypeID"));
            CustomerType customerType = new CustomerType(customerTypeID);

            String customerName = request.getParameter("customerName");

            //Validate Date Of Birth
            String dateString = request.getParameter("dateOfBirth");
            request.setAttribute("dateString",dateString);
            Date dateOfBirth = null;

            if (Validate.regexDateOfBirth(dateString)) {
                request.setAttribute("birthdayWarningMsg","Date of Birth is invalid!");
                warningSign = true;
            }else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed = format.parse(dateString);
                dateOfBirth = new Date(parsed.getTime());
            }

            String sexString = request.getParameter("sex");
            boolean sex = Boolean.parseBoolean(sexString);

            String identityNumber = request.getParameter("identityNumber");
            if (!Validate.regexIdentityNumber(identityNumber)) {
                request.setAttribute("identityNumberWarningMsg","Identity number is invalid!");
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

            Customer newCustomer = new Customer(customerType,customerName,dateOfBirth,sex,identityNumber,phoneNumber,email,address);

            if (warningSign) {
                request.setAttribute("customer",newCustomer);
                showNewForm(request,response);
            } else {
                this.iCustomerService.insertCustomer(newCustomer);
                response.sendRedirect("/customers");
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = this.iCustomerService.selectCustomer(customerID);

        request.setAttribute("existingCustomer",existingCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void updateCustomer(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            int customerID = Integer.parseInt(request.getParameter("customerID"));

            int customerTypeID = Integer.parseInt(request.getParameter("customerTypeID"));
            CustomerType customerType = new CustomerType(customerTypeID);

            String customerName = request.getParameter("customerName");

            String dateString = request.getParameter("dateOfBirth");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(dateString);
            Date dateOfBirth = new Date(parsed.getTime());

            String sexString = request.getParameter("sex");
            boolean sex = Boolean.parseBoolean(sexString);

            String identityNumber = request.getParameter("identityNumber");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            Customer updatedCustomer = new Customer(customerID,customerType,customerName,dateOfBirth,sex,identityNumber,phoneNumber,email,address);
            this.iCustomerService.updateCustomer(updatedCustomer);

            RequestDispatcher dispatcher = request.getRequestDispatcher("customer/edit.jsp");
            dispatcher.forward(request,response);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int customerID = Integer.parseInt(request.getParameter("id"));
        this.iCustomerService.deleteCustomer(customerID);

        List<Customer> customers = this.iCustomerService.selectAllCustomers();
        request.setAttribute("customers",customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        dispatcher.forward(request,response);
    }
}
