<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/28/2021
  Time: 11:18 AM
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
                    <h2>Add new user: </h2>
                </caption>
                <tr>
                    <th>User Name: </th>
                    <td>
                        <input type="text" name="name" id="name" size="45">
                    </td>
                </tr>
                <tr>
                    <th>User Email: </th>
                    <td>
                        <input type="text" name="email" id="email" size="45">
                    </td>
                </tr>
                <tr>
                    <th>User Country: </th>
                    <td>
                        <input type="text" name="country" id="country" size="45">
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
