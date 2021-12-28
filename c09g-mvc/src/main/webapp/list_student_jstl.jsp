<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Student List</h2>
    <a href="/student?actionUser=create">Create new student</a>
    <table border="1">
        <tr>
            <th>No</th>
            <th>Id</th>
            <th>Name</th>
            <th>Date of birth</th>
            <th>Gender</th>
            <th>Grade</th>
        </tr>
        <c:forEach var="studentObj" items="${studentList}" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${studentObj.id}</td>
                <td>
                    <c:out value="${studentObj.name}"/>
                </td>
                <td>
                    <c:out value="${studentObj.dateOfBirth}"/>
                </td>
                <td>
                    <c:if test="${studentObj.gender == 1}">
                        Male
                    </c:if>
                    <c:if test="${studentObj.gender == 0}">
                        Female
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${studentObj.grade < 5}">
                            Poor
                        </c:when>
                        <c:when test="${studentObj.grade < 8}">
                            Good
                        </c:when>
                        <c:otherwise>
                            Excellent
                        </c:otherwise>
                    </c:choose>
            </tr>
        </c:forEach>
    </table>

<%--    ${msg}--%>
<%--<c:out value="${msg}" />--%>
</body>
</html>
