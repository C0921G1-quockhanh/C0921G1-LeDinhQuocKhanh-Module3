import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@javax.servlet.annotation.WebServlet(name = "TranslationServlet", urlPatterns = "/translate")
public class TranslationServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Map<String,String> dictionary = new HashMap<>();

        dictionary.put("hello","Xin chao");
        dictionary.put("how","The nao");
        dictionary.put("book","Quyen sach");
        dictionary.put("computer","May tinh");

        String engWord = request.getParameter("txtSearch");
        String vieWord = dictionary.get(engWord);
        String translated = "";

        if (vieWord != null)
            translated = vieWord;
        else
            translated = "Not found";
        
        request.setAttribute("translated",translated);
        request.getRequestDispatcher("result.jsp")
                .forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
