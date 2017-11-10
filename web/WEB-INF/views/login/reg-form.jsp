<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Sign Up</title>
    <style type="text/css">
        span.error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">

    <form:form method="POST" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" placeholder="E-mail"
                            autofocus="true"/>
                <form:errors path="email"/>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"/>
                <form:errors path="password"/>
            </div>
        </spring:bind>

        <spring:bind path="matchingPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="matchingPassword" class="form-control"
                            placeholder="Confirm your password"/>
                <form:errors path="matchingPassword"/>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
</body>
</html>