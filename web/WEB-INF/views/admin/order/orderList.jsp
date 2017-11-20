<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<a href="/admin/orders/prepared">Prepared</a>
<a href="/admin/orders/done">Done</a>
<a href="/admin/orders">All</a>
<div>
    <c:out value="${status}"/>
</div>
<table border="1" width="100%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Status</th>
        <th colspan="3">Action</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.user.email}"/></td>
            <td><c:out value="${order.status}"/></td>
            <td>
                <c:if test="${order.status==0}">
                    <a name="send" href="/admin/orders/send/${order.id}">send</a>
                </c:if>
                <c:if test="${order.status==1}">
                    <a name="cancel" href="/admin/orders/cancel/${order.id}">cancel</a>
                </c:if>
            </td>
            <td><a name="edit" href="/admin/orders/edit/${order.id}">edit</a></td>
            <td><a name="delete" href="/admin/orders/delete/${order.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
