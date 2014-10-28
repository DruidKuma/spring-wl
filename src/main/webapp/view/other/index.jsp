<%--
  Author: Iurii_Miedviediev
  Date: 9/12/2014
  Time: 1:33 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>
    <title><spring:message code="home.title"/></title>
</head>

<body>

<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h1><spring:message code="home.heading"/></h1>
                    <h3><spring:message code="home.slogan"/></h3>
                    <hr class="intro-divider">
                    <ul class="list-inline intro-social-buttons">
                        <li>
                            <a href="login" class="btn btn-default btn-lg"> <span class="network-name"><spring:message code="header.signin"/></span></a>
                        </li>
                        <li>
                            <a href="register" class="btn btn-default btn-lg"> <span class="network-name"><spring:message code="header.signup"/></span></a>
                        </li>
                    </ul>
                </div>
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
