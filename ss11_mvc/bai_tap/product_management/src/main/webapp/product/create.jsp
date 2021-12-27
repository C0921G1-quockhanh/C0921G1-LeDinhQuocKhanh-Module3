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
    <title>Create new product</title>
    <style>
        .message {
            color: green;
        }
    </style>
</head>
<body>
    <h1>Create new product: </h1>
    <p>
        <c:if test="${requestScope['message_create'] != null}">
            <span class="message">${requestScope['message_create']}</span>
        </c:if>
    </p>

    <p>
        <a href="/products">Back to product list!</a>
    </p>

    <form method="post">
        <fieldset>
            <legend>Product information</legend>
            <table>
                <tr>
                    <td>Name: </td>
                    <td>
                        <input type="text" name="name">
                    </td>
                </tr>
                <tr>
                    <td>Type: </td>
                    <td>
                        <input type="text" name="type">
                    </td>
                </tr>
                <tr>
                    <td>Price: </td>
                    <td>
                        <input type="text" name="price">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Create product">
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</body>
</html>
