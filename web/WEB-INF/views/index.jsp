<%--
  Created by IntelliJ IDEA.
  User: superkostya
  Date: 06.11.17
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="/resources/js/price.js"></script>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div class="container-login">

    <div style="margin-top: 20px;">
        <sec:authorize access="!isAuthenticated()">
            <p><a href="<c:url value="/login" />" role="button">Войти</a></p>
            <p><a href="<c:url value="/registration" />" role="button">Регистрация</a></p>
            <p><a href="<c:url value="/admin" />" role="button">Adminka</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username"/></p>
            <p>Ваша корзина:<a href="<c:url value="/basket/" />" role="button">Корзина</a></p>
            <p><a href="<c:url value="/logout" />" role="button">Выйти</a></p>
        </sec:authorize>
    </div>
</div>

<div id="price-content">
</div>
<div id="login-content">
    <div id="page-login">
        <p><input id="login" type="text" name="login"/></p>
        <p><input id="pass" type="password" name="password"/></p>
        <p><button id="sendAuth">Enter</button></p>
    </div>
</div>
</body>
</html>
