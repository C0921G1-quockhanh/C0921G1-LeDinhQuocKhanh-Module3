<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 12/26/2021
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Simple_Calculator</title>
      <link rel="stylesheet" href="css_bootstrap/css/bootstrap.min.css">
  </head>
  <body>
      <h2>Simple Calculator</h2>

      <form method="post" action="/calculate">
          <table class="table table-dark table-hover" style="width: 50%">
              <tbody>
                  <tr>
                      <td>First Operand: </td>
                      <td>
                          <input type="text" name="first_operand">
                      </td>

                  </tr>
                  <tr>
                      <td>Operator: </td>
                      <td>
                          <select name="operator">
                              <option value="addition">Addition</option>
                              <option value="subtraction">Subtraction</option>
                              <option value="multiplication">Multiplication</option>
                              <option value="division">Division</option>
                          </select>
                      </td>
                  </tr>
                  <tr>
                      <td>Second Operand: </td>
                      <td>
                          <input type="text" name="second_operand">
                      </td>
                  </tr>
                  <tr>
                      <td></td>
                      <td>
                          <input type="submit" value="Calculator">
                      </td>
                  </tr>
              </tbody>
          </table>
      </form>

      <script src="css_bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
