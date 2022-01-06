package controller;

import bean.RentalType;
import bean.ServiceType;
import service.service.IService;
import service.service.Service;
import validation.Validate;

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
        boolean warningSign = false;

        String serviceName = request.getParameter("serviceName");

        String areaStr = request.getParameter("serviceArea");
        if (!Validate.regexPositiveInteger(areaStr)) {
            request.setAttribute("serviceAreaWarningMsg","Service area is invalid!");
            warningSign = true;
        }
        int serviceArea = Integer.parseInt(areaStr);

        double rentalCost = Double.parseDouble(request.getParameter("rentalCost"));
        if (!Validate.isPositive(rentalCost)) {
            request.setAttribute("rentalCostWarningMsg","Rental cost is invalid!");
            warningSign = true;
        }

        int maxPeople = Integer.parseInt(request.getParameter("maxPeople"));

        int rentalTypeID = Integer.parseInt(request.getParameter("rentalTypeID"));
        RentalType rentalType = new RentalType(rentalTypeID);

        int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
        ServiceType serviceType = new ServiceType(serviceTypeID);

        String roomStandard = request.getParameter("roomStandard");
        String extraAmenity = request.getParameter("extraAmenity");

        double poolArea = Double.parseDouble(request.getParameter("poolArea"));
        if (!Validate.isPositive(poolArea)) {
            request.setAttribute("poolAreaWarningMsg","Pool area is invalid!");
            warningSign = true;
        }

        String levelStr = request.getParameter("levels");
        if (!Validate.regexPositiveInteger(levelStr)) {
            request.setAttribute("levelWarningMsg","Levels is invalid!");
            warningSign = true;
        }
        int levels = Integer.parseInt(levelStr);

        bean.Service service = new bean.Service(serviceName,serviceArea,rentalCost,maxPeople,rentalType,serviceType,roomStandard,extraAmenity,poolArea,levels);

        if (warningSign) {
            request.setAttribute("service",service);
            showNewForm(request,response);
        } else {
            this.iService.insertService(service);
            response.sendRedirect("/services");
        }
    }
}
