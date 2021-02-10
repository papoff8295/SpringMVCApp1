<%@ page language="java" contentType="text/html; charset=UTF-8"
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
         pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
${SPRING_SECURITY_LAST_EXCEPTION.message}
<sec:authorize access="isAnonymous()">
<form action="people/login" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit"/></td>
        </tr>
    </table>
</form>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <form action="/logout" method='POST'>
        <a class="btn btn-info mr-1" href="/people/"><sec:authentication property="principal.authRequestDto.personName"/> <spring:message code="app.profile"/></a>
        <input name="submit" type="submit" value="submit"/>
    </form>
</sec:authorize>
</body>
</html>