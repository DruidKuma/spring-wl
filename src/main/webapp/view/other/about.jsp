<%--
  Author: Iurii Miedviediev
  Date: 10/3/14
  Time: 10:02 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
    <%@ include file="../base/headconfig.jsp" %>
    <title><spring:message code="about.title"/></title>
</head>

<body>

<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <div class="row">
            <div  style="width: 70%;" class="center-block">
                <h1 style="font-family: 'Shadows Into Light', cursive;"><spring:message code="about.idea"/></h1>
                <p style="text-align: left"><spring:message code="about.first"/></p>
                <p style="text-align: left"><spring:message code="about.second"/></p>
                <p style="text-align: left"><spring:message code="about.third"/></p>
                <ul class="list-inline">
                    <li>
                        <a href="/technologies"><spring:message code="footer.usedtechs"/></a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="/admin/diagrams"><spring:message code="about.diagrams"/></a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>

    </div>
    <!-- /.container -->

</div>
<!-- /.Content -->

<%@ include file="../base/footer.jsp" %>

<!-- jQuery Version 1.11.0 -->
<script src="/js/jquery-1.11.0.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>
