<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/23/2021
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>After_Discount</title>
</head>
<body>
    <h2>Product Discount Calculator</h2>
    <p>Product Description: <%=request.getAttribute("productDescription")%></p>
    <p>Product Price: <%=request.getAttribute("price")%></p>
    <p>Discount Percent: <%=request.getAttribute("discountPercent")%></p>
    <p>Discount Amount: <%=request.getAttribute("discountAmount")%></p>
    <p>Discount Price: <%=request.getAttribute("discountPrice")%></p>
</body>
</html>
