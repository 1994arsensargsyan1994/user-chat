<%--
  Created by IntelliJ IDEA.
  User: arsen
  Date: 20.04.20
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%=request.getSession().getAttribute("user").toString() %>
<a href="/logout">Logout</a>
</body>
</html>
