<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="100%">
    <tr>
        <th>id</th>
        <th>name</th>
         <th colspan="2">Action</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><a id="edit" href="/admin/product/${product.id}">edit</a></td>
            <td><a id="delete" href="/admin/user/delete/${product.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
