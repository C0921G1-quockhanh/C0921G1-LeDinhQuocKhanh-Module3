<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/23/2021
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Currency_Converter</title>
    <style>
      .converter {
        height: 270px; width: 250px;
        margin: 0;
        padding: 10px;
        border: 2px solid gray;
      }

      .converter input {
        padding: 5px; margin: 5px;
      }
    </style>
  </head>
  <body>
      <div class="converter">
          <h2>Currency Converter</h2>
          <form action="/convert" method="post">
              <p>Rate:</p>
              <input type="text" name="rate" placeholder="RATE">
              <p>USD:</p>
              <input type="text" name="usd" placeholder="USD"> <br>
              <input type="submit" value="Converter" id="submit" style="color: darkslategrey">
          </form>
      </div>
  </body>
</html>
