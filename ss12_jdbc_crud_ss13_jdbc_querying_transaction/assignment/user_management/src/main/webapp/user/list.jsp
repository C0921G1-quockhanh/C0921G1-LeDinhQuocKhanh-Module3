<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/28/2021
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
    <div align="center">
        <h1>User Management</h1>
        <h2>
            <a href="/users?action=create">Add new user: </a>
        </h2>
        <h2>
            <a href="/users?action=find_by_country">Find users by country: </a>
        </h2>
        <h2>
            <a href="/users?action=order_by">Order by name: </a>
        </h2>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption>
                <h2>List of Users</h2>
            </caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>
            </tr>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <c:out value="${user.country}"/>
                    </td>
                    <td>
                        <a href="/users?action=edit&id=${user.id}">Edit</a>
                        <a href="/users?action=delete&id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</body>
</html>
