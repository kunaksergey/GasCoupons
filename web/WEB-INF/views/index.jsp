<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="/resources/js/price.js"></script>
    <script src="/resources/js/jquery.cookie.js"></script>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<div class="container-login">

    <div style="margin-top: 20px;">
        <p><a href="<c:url value="/registration"/>" role="button">Регистрация</a></p>
        <p><a href="<c:url value="/admin" />" role="button">Adminka</a></p>
        <p>Корзина:<a id="basket-link" href="#">Корзина</a></p>
    </div>
</div>


<a id="delete-token" href="#">del token</a>
<a id="change-token" href="#">change token</a>
<div id="login-content">
    <div id="page-login">
        <p><a id="login-close" href="#">Close</a></p>
        <p><label for="login" class="error"></label></p>
        <p><input id="login" type="text" name="login"/></p>
        <p><input id="pass" type="password" name="password"/></p>
        <p>
            <button id="sendAuth">Enter</button>
        </p>
    </div>
</div>

<div id="price">
    <div id="price-content">
        <div id="price-body"></div>
    </div>
</div>

<div id="basket">
    <div id="basket-content">
        <p><a id="basket-close" href="#">Close</a></p>
        <div id="basket-body"></div>
    </div>
</div>
</body>
</html>
