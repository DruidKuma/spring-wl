<%--
  Author: Iurii_Miedviediev
  Date: 9/11/2014
  Time: 3:54 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

    <title><spring:message code="login.title"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>

<div class="intro-header">

    <div class="container">

        <div class="intro-message">

            ${error}
            ${msg}

                <spring:message code="register.login" var="userLogin"/>
                <spring:message code="register.password" var="userPassword"/>

            <form class="form-signin" role="form" action="/j_spring_security_check" method="post">
                <h2 class="form-signin-heading"><spring:message code="login.signin"/></h2>
                <input type="text" class="form-control" name="login" placeholder="${userLogin}" required autofocus>
                <input type="password" class="form-control" name="password" placeholder="${userPassword}" required>
                <label class="checkbox">
                    <input type="checkbox" name="remember-me"> <spring:message code="login.remember"/>
                </label>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="login.submit"/></button>
            </form>
        </div>

    </div> <!-- /container -->
</div>

<%@ include file="../base/footer.jsp" %>
</body>
</html>
