<%--
  Created by IntelliJ IDEA.
  User: Duc Thuong Nguyen
  Date: 5/17/2022
  Time: 6:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm delete</title>
</head>
<body>
<form method="post">
    <input type="number" name="id" value="${deleteUser.id}">
    <input type="text" name="name" value="${deleteUser.name}">
    <input type="text" name="email" value="${deleteUser.email}">
    <input type="text" name="country" value="${deleteUser.country}">
    <button>Confirm delete</button>
</form>
</body>
</html>
