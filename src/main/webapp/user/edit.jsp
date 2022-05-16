<%--
  Created by IntelliJ IDEA.
  User: Duc Thuong Nguyen
  Date: 5/16/2022
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<form method="post">
    <input type="number" name="id" value="${editUser.id}">
    <input type="text" name="name" value="${editUser.name}">
    <input type="text" name="email" value="${editUser.email}">
    <input type="text" name="country" value="${editUser.country}">
    <button>Edit</button>
</form>
</body>
</html>
