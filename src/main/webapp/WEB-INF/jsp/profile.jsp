<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="people" tagdir="/WEB-INF/tags" %>

<html>
<%--<jsp:include page="fragments/headTag.jsp"/>--%>

<body>
<%--<jsp:include page="fragments/bodyHeader.jsp"/>--%>

<div class="jumbotron pt-4">
    <div class="container">
        <%--@elvariable id="userTo" type="ru.javawebinar.topjava.to.UserTo"--%>
        <div class="row">
            <div class="col-5 offset-3">
                <h3>${userTo.name} <spring:message text="Register"/></h3>
                <form:form class="form-group" modelAttribute="personDTO" method="post" action="${register ? 'register' : 'login'}"
                           charset="utf-8" accept-charset="UTF-8">

                    <input name="id" value="${userTo.id}" type="hidden">
                    <people:inputField labelCode="user.name" name="name"/>
                    <people:inputField labelCode="user.email" name="email"/>
                    <people:inputField labelCode="user.password" name="password" inputType="password"/>
                    <people:inputField labelCode="user.caloriesPerDay" name="caloriesPerDay" inputType="number"/>

                    <div class="text-right">
                        <a class="btn btn-secondary" href="#" onclick="window.history.back()">
                            <span class="fa fa-close"></span>
                            <spring:message code="common.cancel"/>
                        </a>
                        <button type="submit" class="btn btn-primary">
                            <span class="fa fa-check"></span>
                            <spring:message code="common.save"/>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="fragments/footer.jsp"/>--%>
</body>
</html>