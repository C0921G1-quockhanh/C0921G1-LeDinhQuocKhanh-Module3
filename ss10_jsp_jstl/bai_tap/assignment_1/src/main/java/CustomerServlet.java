import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/", "/customer"})
public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("Ronaldo","05-02-1985","Portugal","resources/img/ronaldo.jpg"));
        customers.add(new Customer("Messi","24-06-1987","Argentina","resources/img/messi.jpg"));
        customers.add(new Customer("Neymar","05-02-1992","Brazil","resources/img/neymar.jpg"));
        customers.add(new Customer("Haaland","21-07-2000","Norway","resources/img/haaland.jpg"));
        
        request.setAttribute("customers",customers);
        request.getRequestDispatcher("customer_list.jsp")
                .forward(request,response);
    }
}
