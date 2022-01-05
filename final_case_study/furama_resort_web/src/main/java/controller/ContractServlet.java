package controller;

import bean.*;
import service.contract.ContractService;
import service.contract.IContractService;
import service.customer.CustomerService;
import service.customer.ICustomerService;
import service.employee.EmployeeService;
import service.employee.IEmployeeService;
import service.service.IService;

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

@WebServlet(name = "ContractServlet",urlPatterns = "/contracts")
public class ContractServlet extends HttpServlet {

    private IContractService iContractService = new ContractService();
    private IEmployeeService iEmployeeService = new EmployeeService();
    private ICustomerService iCustomerService = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        try {
            switch (action) {
                case "create":
                    insertContract(request,response);
                    break;

                case "create_detail":
                    insertDetailContract(request,response);
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

                case "create_detail":
                    showDetailForm(request,response);
                    break;

                default:
                    listContract(request,response);
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listContract(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("contract/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Employee> employees = this.iEmployeeService.selectAllEmployees();
        request.setAttribute("employees",employees);

        List<Customer> customers = this.iCustomerService.selectAllCustomers();
        request.setAttribute("customers",customers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("contract/create.jsp");
        dispatcher.forward(request,response);
    }

    private void insertContract(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {

        try {
            String startString = request.getParameter("startDate");
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed1 = format1.parse(startString);
            Date startDate = new Date(parsed1.getTime());

            String endString = request.getParameter("endDate");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed2 = format2.parse(endString);
            Date endDate = new Date(parsed2.getTime());

            double deposit = Double.parseDouble(request.getParameter("deposit"));

            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            Employee employee = new Employee(employeeID);

            int customerID = Integer.parseInt(request.getParameter("customerID"));
            Customer customer = new Customer(customerID);

            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            Service service = new Service(serviceID);

            Contract newContract = new Contract(startDate,endDate,deposit,employee,customer,service);
            this.iContractService.insertContract(newContract);

            RequestDispatcher dispatcher = request.getRequestDispatcher("contract/create.jsp");
            dispatcher.forward(request,response);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void showDetailForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Contract> contracts = this.iContractService.selectAllContracts();
        request.setAttribute("contracts",contracts);

        List<AccompaniedService> accompaniedServices = this.iContractService.selectAllAccompaniedServices();
        request.setAttribute("accompaniedServices",accompaniedServices);

        RequestDispatcher dispatcher = request.getRequestDispatcher("contract/create_detail.jsp");
        dispatcher.forward(request,response);
    }

    private void insertDetailContract(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int contractID = Integer.parseInt(request.getParameter("contractID"));
        Contract contract = new Contract(contractID);

        int accompaniedServiceID = Integer.parseInt(request.getParameter("accompaniedServiceID"));
        AccompaniedService accompaniedService = new AccompaniedService(accompaniedServiceID);

        int quantity = Integer.parseInt(request.getParameter("quantity"));

        DetailContract newDetailContract = new DetailContract(contract,accompaniedService,quantity);
        this.iContractService.insertDetailContract(newDetailContract);

        RequestDispatcher dispatcher = request.getRequestDispatcher("contract/create_detail.jsp");
        dispatcher.forward(request,response);
    }
}
