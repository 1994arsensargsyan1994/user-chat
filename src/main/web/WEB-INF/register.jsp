<%@ page import="java.io.IOException" %><%--
  Created by IntelliJ IDEA.
  User: arsen
  Date: 16.04.20
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery.js" type="text/javascript"></script>
    <script src="/js/main.js" type="text/javascript"></script>
</head>
<%!
    String getParamValue(HttpServletRequest request,String name){
        String value = request.getParameter(name);
        return  value == null ? "" : value;
    }
%>

<form id="register-form" action="/register" method="post" enctype="multipart/form-data">
<%--    enctype="multipart/form-data"--%>

    <input id="name-input" type="text"
    placeholder="<c:out value="${requestScope.errorName != null ? requestScope.errorName : 'Name'}"/>"
    name="name"
    class="<c:out value="${requestScope.errorName != null ? 'danger-placeholder' : ''}"/>"
    required/>
    <br>
    <input id="surname-input" type="text"
           placeholder="<c:out value="${requestScope.errorSurname != null ? requestScope.errorSurname : 'Surname'}"/>"
           class="<c:out value="${requestScope.errorSurname != null ? 'danger-placeholder' : ''}"/>"
           name="surname" required/>
    <br>
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
    <input id="confirm-password-input" type="password"
           placeholder="<c:out value="${requestScope.errorConfirmPassword != null ? requestScope.errorConfirmPassword : 'Confirm Password'}"/>"
           name="confirmPassword"
           class="<c:out value="${requestScope.errorConfirmPassword != null ? 'danger-placeholder' : ''}"/>"
           required/>
    <br>
    <p>Select Profile Image</p>
    <input type="file" name="file" accept="image/*">
    <br>
    <button onclick="doRegister()">create</button>

<%--    <input type="submit" name="Register">--%>
    <a href="/login">login</a>

</form>
</body>
</html>
