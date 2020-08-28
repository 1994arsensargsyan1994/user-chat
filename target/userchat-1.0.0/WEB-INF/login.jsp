<%--
  Created by IntelliJ IDEA.
  User: arsen
  Date: 16.04.20
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
    <script src="../js/jquery.js" type="text/javascript"></script>
    <script src="../js/main.js" type="text/javascript"></script>
</head>
<body>
<form id="login-form" action="/login" method="post">

    <c:if test="${sessionScope.successfully != null }">
   <p style="color:blue">You are Successfully Registered!</p>
   <c:remove scope="session" var="successfully"/>
   </c:if>


    <c:if test="${requestScope.wrongEmailPassword != null}">
        <p class="danger">
            <c:out value="${requestScope.wrongEmailPassword}"/>
        </p>
    </c:if>
    <p hidden="hidden" class="danger">Wrong Email Format!</p>
    <input id="email-input" type="text"
           placeholder="<c:out value="${requestScope.errorEmail != null ? requestScope.errorEmail : 'Email'}"/>"
           name="email"
           class="<c:out value="${requestScope.errorEmail != null ? 'danger-placeholder' : ''}"/>"
           required/>
    <br>
    <input id="password-input" type="password"
           placeholder="<c:out value="${requestScope.errorPassword != null ? requestScope.errorPassword : 'Password'}"/>"
           name="password"
           class="<c:out value="${requestScope.errorPassword != null ? 'danger-placeholder' : ''}"/>"
           required/>
    <br>
    <button onclick="doLogin()">Login</button>
<%--    <input type="submit" value="Login">--%>
    <a href="/register">Register</a>
</form>
</body>
</html>