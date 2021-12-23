import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculateDiscountServlet", urlPatterns = "/display-discount")
public class CalculateDiscountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productDescription = request.getParameter("product_description");
        double price = Double.parseDouble(request.getParameter("product_price"));
        double discountPercent = Double.parseDouble(request.getParameter("discount_percent"));

        double discountAmount = price * discountPercent * 0.01;
        double discountPrice = price - discountAmount;
        
        request.setAttribute("productDescription",productDescription);
        request.setAttribute("price",price);
        request.setAttribute("discountPercent",discountPercent);
        request.setAttribute("discountAmount",discountAmount);
        request.setAttribute("discountPrice",discountPrice);
        
        request.getRequestDispatcher("result.jsp")
                .forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
