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
            <a href="/users">Back to user list!</a>
        </h2>
    </div>

    <div align="center">
        <form method="post">
            <table border="1" cellpadding="5">
                <caption>
                    <h2>Edit user: </h2>
                </caption>

                <c:if test="${existingUser != null}">
                    <input type="hidden" name="id" value="<c:out value="${existingUser.id}"/>">
                </c:if>

                <tr>
                    <th>User name: </th>
                    <td>
                        <input type="text" name="name" size="45" value="<c:out value="${existingUser.name}"/>">
                    </td>
                </tr>
                <tr>
                    <th>User email: </th>
                    <td>
                        <input type="text" name="email" size="45" value="<c:out value="${existingUser.email}"/>">
                    </td>
                </tr>
                <tr>
                    <th>User country: </th>
                    <td>
                        <input type="text" name="country" size="45" value="<c:out value="${existingUser.country}"/>">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
