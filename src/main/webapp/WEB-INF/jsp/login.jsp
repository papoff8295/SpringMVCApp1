<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp"/>
<head>
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
            <h4><a href="/register">Зарегистрироваться</a></h4>
        </div>
    </form>
</div>
<%--&lt;%&ndash;<div>&ndash;%&gt;--%>
<%--    <form:form method="POST" modelAttribute="userForm">--%>
<%--        <h2>Регистрация</h2>--%>
<%--        <div>--%>
<%--            <form:input type="text" path="personName" placeholder="Username" autofocus="true"></form:input>--%>
<%--            <form:errors path="personName"></form:errors>--%>
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
<%--        <button type="submit">Login</button>--%>

<%--    </form:form>--%>
<%--    <a href="/">Главная</a>--%>
<%--    <h4><a href="/register">Зарегистрироваться</a></h4>--%>
<%--</div>--%>
</body>
</html>