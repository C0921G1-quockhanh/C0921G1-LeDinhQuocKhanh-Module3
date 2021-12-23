<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/23/2021
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Simple Dictionary</title>
  </head>
  <body>
  <h2>Vietnamese Dictionary</h2>
      <form method="post" action="/translate">
          <input type="text" name="txtSearch" placeholder="Enter your word: ">
          <input type="submit" value="Search" id="submit">
      </form>
  </body>
</html>
