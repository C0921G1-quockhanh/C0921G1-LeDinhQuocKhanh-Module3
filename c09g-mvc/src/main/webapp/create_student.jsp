<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Create student</h2>
    <form action="/student" method="post">
        <input type="hidden" name="actionUser" value="create">
        <div>
            Name:
            <input type="text" name="nameStudent">
        </div>
        <div>
            Date of birth:
            <input type="date" name="dateOfBirth">
        </div>
        <div>
            <input type="submit" value="Register">
        </div>
    </form>
</body>
</html>
