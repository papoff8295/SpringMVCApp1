<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<jsp:include page="fragment/head.jsp"/>
<head>
    <title>Главная</title>

</head>
<body>
<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>

    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">Войти</a></h4>
        <form:form modelAttribute="userRegister">
            <h4><a href="/register">Зарегистрироваться</a></h4>
            ${userRegister}
        </form:form>

    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Выйти</a></h4>

    </sec:authorize>
    <h4><a href="/news">Новости (только пользователь)</a></h4>
    <h4><a href="/admin">Пользователи (только админ)</a></h4>
</div>
</body>
</html>