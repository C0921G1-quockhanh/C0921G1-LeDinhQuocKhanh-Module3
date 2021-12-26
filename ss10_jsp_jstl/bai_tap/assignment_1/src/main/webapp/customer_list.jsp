<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/25/2021
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Customer_List</title>
</head>
<body>
    <h2>Customer List</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Date of Birth</th>
            <th>Address</th>
            <th>Image</th>
        </tr>

        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>
                    <c:out value="${customer.name}"/>
                </td>
                <td>
                    <c:out value="${customer.dateOfBirth}"/>
                </td>
                <td>
                    <c:out value="${customer.address}"/>
                </td>
                <td>
                    <img src="<c:out value="${customer.image}"/>" width="100px" height="100px" alt="footballer_image">
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
