<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/23/2021
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Product_Discount_Calculator</title>
  </head>
  <body>
      <h2>Product Discount Calculator</h2>
      <form action="/display-discount" method="post">
          <input type="text" name="product_description" placeholder="description of product">
          <input type="text" name="product_price" placeholder="price of product">
          <input type="text" name="discount_percent" placeholder="discount percent">
          <input type="submit" value="Calculate Discount" id="submit">
      </form>
  </body>
</html>
