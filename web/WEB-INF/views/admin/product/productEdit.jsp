<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="product-edit">
    <div class="edit-content">
        <form:form method="POST" modelAttribute="product" class="form-signin">
            <table border="1" width="100%">
                <tr>
                    <th>ID</th>
                    <th>Product</th>
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
