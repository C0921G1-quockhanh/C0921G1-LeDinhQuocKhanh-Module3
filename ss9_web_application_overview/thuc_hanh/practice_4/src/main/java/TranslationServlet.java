import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TranslationServlet", value = "/translate")
public class TranslationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> dictionary = new HashMap<>();

        dictionary.put("hello","Xin chao");
        dictionary.put("how","The nao");
        dictionary.put("book","Quyen vo");
        dictionary.put("computer","May tinh");

        String engWord = request.getParameter("txtSearch");
        String vieWord = dictionary.get(engWord);
        String result = "";

        if (vieWord != null)
            result = vieWord;
        else
            result = "Not found";
        
        request.setAttribute("translated_word",result);
        request.getRequestDispatcher("result.jsp")
                .forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
