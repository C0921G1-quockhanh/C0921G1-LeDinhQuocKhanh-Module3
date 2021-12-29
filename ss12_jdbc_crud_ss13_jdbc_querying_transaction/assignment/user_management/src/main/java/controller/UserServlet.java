package controller;

import bean.User;
import service.IUserService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService iUserService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        try {
            switch (action) {
                case "create":
                    insertUser(request,response);
                    break;

                case "edit":
                    updateUser(request,response);
                    break;

                case "find_by_country":
                    findUserByCountry(request,response);
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
                    deleteUser(request,response);
                    break;

                case "find_by_country":
                    showFindUserForm(request,response);
                    break;

                case "order_by":
                    orderByName(request,response);
                    break;

                case "permission":
                    addUserPermission(request,response);
                    break;

                case "test-without-tran":
                    testWithoutTran(request,response);
                    break;

                case "test-use-tran":
                    testUseTran(request,response);
                    break;

                default:
                    listUser(request,response);
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void testUseTran(HttpServletRequest request,HttpServletResponse response) {
        this.iUserService.insertUpdateUseTransaction();
    }

    private void testWithoutTran(HttpServletRequest request,HttpServletResponse response) {
        this.iUserService.insertUpdateWithoutTransaction();
    }

    private void addUserPermission(HttpServletRequest request,HttpServletResponse response) {
        User user = new User("quan","quan.nguyen@codegym.vn","Viet Nam");
        int[] permissions = {1,2,4};
        this.iUserService.addUserTransaction(user,permissions);
    }

    private void showFindUserForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/find_by_country.jsp");
        dispatcher.forward(request,response);
    }

    private void findUserByCountry(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        String existingCountry = request.getParameter("country");
        List<User> users = this.iUserService.findUserByCountry(existingCountry);

        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/find_by_country.jsp");
        dispatcher.forward(request,response);
    }

    private void orderByName(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<User> users = this.iUserService.orderByName();
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/order_by_name.jsp");
        dispatcher.forward(request,response);
    }

    private void listUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<User> users = this.iUserService.selectAllUsers();
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
    }

    private void insertUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User newUser = new User(name,email,country);
//        this.iUserService.insertUser(newUser);
        this.iUserService.insertUserStore(newUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request,response);
    }

    private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
//        User existingUser = this.iUserService.selectUser(id);
        User existingUser = this.iUserService.getUserByID(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("existingUser",existingUser);
        dispatcher.forward(request,response);
    }

    private void updateUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User updatedUser = new User(id,name,email,country);
        this.iUserService.updateUser(updatedUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.iUserService.deleteUser(id);

        List<User> users = this.iUserService.selectAllUsers();
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request,response);
    }
}
