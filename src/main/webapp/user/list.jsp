<%--
  Created by IntelliJ IDEA.
  User: Duc Thuong Nguyen
  Date: 5/16/2022
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show User List</title>
</head>
<body>
<h2>Show User List</h2>
<a href="users?action=create">Create new user</a>
<br>
<c:forEach items="${list}" var="user">
    ${user.id}
    | ${user.name}
    | ${user.email}
    | ${user.country}
    | <a href="users?action=edit&id=${user.id}">Edit</a>
    | <a href="users?action=delete&id=${user.id}">Delete</a>
    <br>
</c:forEach>
</body>
</html>
