<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/23/2021
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login_Status</title>
</head>
<body>
    <h1 style="color: red">
        <%= request.getAttribute("loginResult")%>
    </h1>
</body>
</html>
