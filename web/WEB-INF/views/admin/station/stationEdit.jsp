<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Station</title>
</head>
<body>
<div id="user-edit">
    <div class="edit-content">
        <form:form method="POST" modelAttribute="station" class="form-signin">
            <table border="1" width="100%">
                <tr>
                    <th>ID</th>
                    <th>E-mail</th>
                </tr>

                <tr>
                    <td>
                        <form:input readonly="true" path="id" class="form-control"/>
                    </td>
                    <td>
                        <form:input path="name" class="form-control"/>
                    </td>
                </tr>
            </table>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
    </div>
</div>
</body>
</html>

