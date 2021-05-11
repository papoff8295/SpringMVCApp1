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
        <th>Roles</th>
        </thead>
        ${error}
        <c:forEach items="${allUsers}" var="people">
            <tr>
                <td><a href="/admin/${people.id}">${people.id}</a></td>
                <td>${people.name}</td>
                <td>${people.age}</td>
                <td>${people.email}</td>
                <td>
                    <c:forEach items="${people.roles}" var="role">${role.name}; </c:forEach>
                </td>
                <td>
                    <form method="POST" action="/admin">
                        <input type="hidden" name="userId" value="${people.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>

                </td>

            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
</div>
</body>
</html>