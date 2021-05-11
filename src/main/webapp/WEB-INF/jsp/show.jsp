<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp"/>
<head>
    <title>Log in with your account</title>
</head>

<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Age</th>
        <th>Email</th>
        <th>Roles</th>
        </thead>

            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.password}</td>
                <td>${person.age}</td>
                <td>${person.email}</td>

                <td>
                    <c:forEach items="${person.roles}" var="role">${role.name}; </c:forEach>
                </td>
                <td>
                    <form method="POST" action="/admin">
                        <input type="hidden" name="userId" value="${person.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>

                </td>

            </tr>

    </table>
    <a href="/">Главная</a>
</div>
</body>
</html>