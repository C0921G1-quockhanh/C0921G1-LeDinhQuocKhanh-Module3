<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/29/2021
  Time: 8:41 AM
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
            <a href="/users">Back to user list!</a>
        </h2>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption>
                <h2>List of Users after ordering</h2>
            </caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
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
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
