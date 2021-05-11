<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp"/>
<head>
    <title>Регистрация</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="name" placeholder="Username" autofocus="true"></form:input>
            <form:errors path="name"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="number" path="age" placeholder="Age"></form:input>
<%--            <div style="color:red" c:if=${fields.hasErrors('name')} th:errors="*{name}">Name error</div>--%>
            <form:errors path="age" cssClass="error"></form:errors>
        </div>
        <div>
            <form:input type="text" path="email" placeholder="Email"></form:input>
            <form:errors path="email"></form:errors>
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password"></form:input>
            <form:errors path="password"></form:errors>
        </div>
<%--        <div>--%>
<%--            <form:input type="password" path="passwordConfirm"--%>
<%--                        placeholder="Confirm your password"></form:input>--%>
<%--            <form:errors path="password"></form:errors>--%>
<%--                ${passwordError}--%>
<%--        </div>--%>
        <button type="submit">Зарегистрироваться</button>
    </form:form>
    <a href="/">Главная</a>
</div>
</body>
</html>