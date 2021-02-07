<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<body>


<div class="jumbotron py-0">
    <div class="container">
        <c:if test="${param.error}">
            <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message"><spring:message code="${param.message}"/></div>
        </c:if>
        <sec:authorize access="isAnonymous()">
            <div class="pt-4">
                <a class="btn btn-lg btn-info" href="profile/register"><spring:message text="Registration"/> &raquo;</a>
            </div>
        </sec:authorize>

<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>

    function login(username, password) {
        setCredentials(username, password);
        $("#login_form").submit();
    }
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
</body>
</html>