<%--
  Author: Iurii_Miedviediev
  Date: 9/10/2014
  Time: 6:25 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>
    <link href="css/signin.css" rel="stylesheet">
    <title><spring:message code="register.title"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>
<div class="intro-header">

    <div class="container">

        <div class="intro-message">

            <h2 class="form-signin-heading"><spring:message code="register.message"/>:</h2>

            <!-- Messages -->
            <spring:message code="register.login" var="userLogin"/>
            <spring:message code="register.password" var="userPass"/>
            <spring:message code="register.repeat" var="userRepeat"/>
            <spring:message code="register.name" var="userName"/>
            <spring:message code="register.surname" var="userSurname"/>
            <spring:message code="register.email" var="userEmail"/>

            <form:form method="POST" class="form-signin" modelAttribute="registrationForm" action="register">
                <form:errors path="*" cssClass="errorblock" element="div" />
                <form:input cssClass="form-control" path="credentials.login" placeholder="${userLogin}" />
                <form:input type="password" cssClass="form-control" path="credentials.password" placeholder="${userPass}" />
                <form:input type="password" cssClass="form-control" path="passRepeat" placeholder="${userRepeat}"/>
                <form:input cssClass="form-control" path="user.firstName" placeholder="${userName}" />
                <form:input cssClass="form-control" path="user.lastName" placeholder="${userSurname}"/>
                <form:input cssClass="form-control" path="user.email" placeholder="${userEmail}"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="register.submit"/></button>
            </form:form>

        </div>

    </div>
</div>
<%@ include file="../base/footer.jsp" %>
</body>
</html>
