<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/27/2021
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Product By Name</title>
</head>
<body>
    <h1>View Product By Name: </h1>
    <p>
        <a href="/products">Back to product list!</a>
    </p>

    <p>
        <c:if test="${requestScope['message_view_by_name'] != null}">
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
        </c:if>
    </p>

    <form method="post">
        <table>
            <tr>
                <td>Name: </td>
                <td>
                    <input type="text" name="name">
                </td>
                <td>
                    <input type="submit" value="View product">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
