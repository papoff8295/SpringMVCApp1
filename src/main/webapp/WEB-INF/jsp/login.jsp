<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
    <form method="POST" action="/login">
        <h2>Вход в систему</h2>
        <div>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" placeholder="Password"/>
            <button type="submit">Log In</button>
            <h4><a href="/registration">Зарегистрироваться</a></h4>
        </div>
    </form>
</div>
<%--<div>--%>
<%--    <form:form method="POST" modelAttribute="userForm">--%>
<%--        <h2>Регистрация</h2>--%>
<%--        <div>--%>
<%--            <form:input type="text" path="personName" placeholder="Username" autofocus="true"></form:input>--%>
<%--            <form:errors path="personName"></form:errors>--%>
<%--                ${usernameError}--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <form:input type="password" path="password" placeholder="Password"></form:input>--%>
<%--        </div>--%>
<%--        &lt;%&ndash;        <div>&ndash;%&gt;--%>
<%--        &lt;%&ndash;            <form:input type="password" path="passwordConfirm"&ndash;%&gt;--%>
<%--        &lt;%&ndash;                        placeholder="Confirm your password"></form:input>&ndash;%&gt;--%>
<%--        &lt;%&ndash;            <form:errors path="password"></form:errors>&ndash;%&gt;--%>
<%--        &lt;%&ndash;                ${passwordError}&ndash;%&gt;--%>
<%--        &lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--        <button type="submit">Зарегистрироваться</button>--%>
<%--    </form:form>--%>
<%--    <a href="/">Главная</a>--%>
<%--</div>--%>
</body>
</html>