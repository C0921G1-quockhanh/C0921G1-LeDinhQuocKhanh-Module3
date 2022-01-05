package controller;

import bean.RentalType;
import bean.ServiceType;
import service.service.IService;
import service.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServiceServlet",urlPatterns = "/services")
public class ServiceServlet extends HttpServlet {

    private IService iService = new Service();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        try {
            switch (action) {
                case "create":
                    insertService(request,response);
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

                default:
                    listService(request,response);
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listService(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("service/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("service/create.jsp");
        dispatcher.forward(request,response);
    }

    private void insertService(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        String serviceName = request.getParameter("serviceName");
        int serviceArea = Integer.parseInt(request.getParameter("serviceArea"));
        double rentalCost = Double.parseDouble(request.getParameter("rentalCost"));
        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));

        int rentalTypeID = Integer.parseInt(request.getParameter("rentalTypeID"));
        RentalType rentalType = new RentalType(rentalTypeID);

        int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
        ServiceType serviceType = new ServiceType(serviceTypeID);

        String roomStandard = request.getParameter("roomStandard");
        String extraAmenity = request.getParameter("extraAmenity");
        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        int levels = Integer.parseInt(request.getParameter("levels"));

        bean.Service service = new bean.Service(serviceName,serviceArea,rentalCost,maxPeople,rentalType,serviceType,roomStandard,extraAmenity,poolArea,levels);
        this.iService.insertService(service);

        RequestDispatcher dispatcher = request.getRequestDispatcher("service/create.jsp");
        dispatcher.forward(request,response);
    }
}
