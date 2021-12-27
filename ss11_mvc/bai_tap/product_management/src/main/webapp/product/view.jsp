<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/27/2021
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Product</title>
</head>
<body>
    <h1>Product details</h1>
    <p>
        <a href="/products">Back to product list!</a>
    </p>

    <table>
        <tr>
            <td>Name: </td>
            <td>${requestScope['product'].getName()}</td>
        </tr>
        <tr>
            <td>Type: </td>
            <td>${requestScope['product'].getType()}</td>
        </tr>
        <tr>
            <td>Price: </td>
            <td>${requestScope['product'].getPrice()}</td>
        </tr>
    </table>
</body>
</html>
