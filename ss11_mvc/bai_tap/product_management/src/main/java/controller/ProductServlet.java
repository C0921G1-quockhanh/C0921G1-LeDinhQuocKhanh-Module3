package controller;

import bean.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet",urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "create":
                createProduct(request,response);
                break;

            case "edit":
                updateProduct(request,response);
                break;

            case "delete":
                deleteProduct(request,response);
                break;

            case "viewByName":
                viewProductByName(request,response);
                break;

            default:
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";

        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;

            case "edit":
                showEditForm(request,response);
                break;

            case "delete":
                showDeleteForm(request,response);
                break;

            case "view":
                viewProduct(request,response);
                break;

            case "viewByName":
                showProductForm(request,response);
                break;

            default:
                listProducts(request,response);
        }
    }

    private void listProducts(HttpServletRequest request,HttpServletResponse response) {
        List<Product> products = this.productService.findAll();
        request.setAttribute("products",products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showProductForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/viewByName.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void viewProductByName(HttpServletRequest request,HttpServletResponse response) {
        String name = request.getParameter("name");
        List<Product> products = this.productService.findAll();
        RequestDispatcher dispatcher;
        Product selectedProduct = null;
        boolean checkProductByName = false;
        int index = 0;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getName().equals(name)) {
                checkProductByName = true;
                index = i;
                break;
            }
        }

        if (checkProductByName)
            selectedProduct = products.get(index);

        if (selectedProduct == null)
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("product",selectedProduct);
            request.setAttribute("message_view_by_name","Selected product was displayed");
            dispatcher = request.getRequestDispatcher("product/viewByName.jsp");
        }

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void showCreateForm(HttpServletRequest request,HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request,HttpServletResponse response) {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        int id = (int)(Math.random()*500);

        Product product = new Product(id,name,type,price);
        this.productService.save(product);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("message_create","New product was created!");

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request,HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;

        if (product == null)
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("product",product);
            dispatcher = request.getRequestDispatcher("product/edit.jsp");
        }

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request,HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;

        if (product == null)
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            product.setName(name);
            product.setType(type);
            product.setPrice(price);
            this.productService.update(id,product);

            request.setAttribute("product",product);
            request.setAttribute("message_edit","Product information was updated!");
            dispatcher = request.getRequestDispatcher("product/edit.jsp");
        }

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest request,HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;

        if (product == null)
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("product",product);
            dispatcher = request.getRequestDispatcher("product/delete.jsp");
        }

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request,HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;

        if (product == null)
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            this.productService.remove(id);
            try {
                response.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void viewProduct(HttpServletRequest request,HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;

        if (product == null)
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        else {
            request.setAttribute("product",product);
            dispatcher = request.getRequestDispatcher("product/view.jsp");
        }

        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
