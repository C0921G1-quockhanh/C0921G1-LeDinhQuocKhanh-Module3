import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculate")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double firstOperand = Double.parseDouble(request.getParameter("first_operand"));
        double secondOperand = Double.parseDouble(request.getParameter("second_operand"));
        String operator = request.getParameter("operator");
        String message = "";

        try {
            double result = Calculator.calculate(firstOperand,secondOperand,operator);
            request.setAttribute("result",result);
        }
        catch (Exception e) {
            message = e.getMessage();
        }

        request.setAttribute("exception",message);
        request.getRequestDispatcher("result.jsp")
                .forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
