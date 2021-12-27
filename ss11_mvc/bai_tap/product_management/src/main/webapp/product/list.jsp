<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/27/2021
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h1>Products</h1>
    <p>
        <a href="/products?action=create">Create new product: </a>
    </p>

    <p>
        <a href="/products?action=viewByName">View product by name: </a>
    </p>

    <table border="1">
        <tr>
            <td>Name</td>
            <td>Type</td>
            <td>Price</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="product" items="${requestScope['products']}">
            <tr>
                <td>
                    <a href="/products?action=view&id=${product.getId()}">${product.getName()}</a>
                </td>
                <td>${product.getType()}</td>
                <td>${product.getPrice()}</td>
                <td>
                    <a href="/products?action=edit&id=${product.getId()}">Edit</a>
                </td>
                <td>
                    <a href="/products?action=delete&id=${product.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
