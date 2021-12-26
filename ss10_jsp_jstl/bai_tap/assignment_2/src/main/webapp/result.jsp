<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/26/2021
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result_Calculator</title>
</head>
<body>
    <h2>Result is: </h2>

    <c:if test="${exception != ''}">
        <c:out value="${exception}"/>
    </c:if>

    <c:if test="${exception == ''}">
        <c:out value="${result}"/>
    </c:if>
</body>
</html>
