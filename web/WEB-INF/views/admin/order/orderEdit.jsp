<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>OrderEdit</title>
</head>
<body>
<h2><c:out value="${order.id}"/></h2>
<h3><c:out value="${order.user.email}"/></h3>

<table border="1" width="100%">
    <tr>
        <th>Product</th>
        <th>Station</th>
        <th>Price</th>
        <th>Count</th>
        <th>Summ</th>
        <th>Action</th>
    </tr>
    <c:forEach var="item" items="${order.orderItemList}">
        <tr>
            <td><c:out value="${item.product.name}"/></td>
            <td><c:out value="${item.station.name}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td><c:out value="${item.count}"/></td>
            <td><c:out value="${item.summ}"/></td>
            <td>
                <c:url var="deleteUrl" value="/admin/orders/delete-item"/>
                <form id="item_${item.id}" action="${deleteUrl}" method="POST">
                    <input type="hidden" name="id"  value="${order.id}"/>
                    <input type="hidden" id="${item.id}" name="itemId" value="${item.id}"/>
                    <input type="submit" value="delete"  class="delete-submit" onClick="return confirm('sure?')"/>
                </form>
            </td>

        </tr>
    </c:forEach>
</table>
<h2><c:out value="${order.summ}"/></h2>
</body>
</html>
