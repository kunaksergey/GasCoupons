<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<div class="container-login">

    <div style="margin-top: 20px;">
        <sec:authorize access="!isAuthenticated()">
            <p><a href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username"/></p>
            <a href="/admin/orders/prepared">Orders</a>
            <a href="/admin/basket/">Baskets</a>
            <a href="/admin/user/">Users</a>
            <a href="/admin/product/">Products</a>
            <a href="/admin/station/">Stations</a>
            <p><a href="<c:url value="/logout" />" role="button">Выйти</a></p>
        </sec:authorize>

    </div>
</div>

</body>
</html>
