<html xmlns:th="http://www.thymeleaf.org">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container" style="width: 300px;">
        <spring:url var = "loginUrl" value = "/j_spring_security_check" />
    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Войти:</h2>
        <div>
            <input type="text" class="form-control" name="username" placeholder="Email address" required autofocus>
        </div>
        <div>
            <input type="password" class="form-control" name="password" placeholder="Password" required>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
    </form>
</div>
</body>

</html>
