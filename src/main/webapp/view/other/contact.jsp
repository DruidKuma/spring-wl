<%--
  Author: DruidKuma
  Date: 10/3/14
  Time: 10:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>
    <title><spring:message code="contact.title"/></title>
</head>

<body>

<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <img style="border-radius: 50%; width: 370px; height: 300px;" src="/img/me.png">
                    <h1><spring:message code="contact.name"/></h1>
                    <hr class="intro-divider">
                    <ul class="list-inline intro-social-buttons">
                        <li>
                            <a href="https://www.facebook.com/DruidKuma" class="btn btn-default btn-lg"><i class="fa fa-facebook fa-fw"></i> <span class="network-name">Facebook</span></a>
                        </li>
                        <li>
                            <a href="https://github.com/druidkuma" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                        </li>
                        <li>
                            <a href="https://www.linkedin.com/profile/view?id=296030996" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span class="network-name">Linkedin</span></a>
                        </li>
                        <li>
                            <a href="https://vk.com/yuriy_medvedev" class="btn btn-default btn-lg"><i class="fa fa-vk fa-fw"></i> <span class="network-name">Vk.com</span></a>
                        </li>
                        <li>
                            <a href="http://instagram.com/druidkuma" class="btn btn-default btn-lg"><i class="fa fa-instagram fa-fw"></i> <span class="network-name">Instagram</span></a>
                        </li>
                        <li>
                            <a href="https://plus.google.com/u/0/112629103346173701719" class="btn btn-default btn-lg"><i class="fa fa-google-plus fa-fw"></i> <span class="network-name">Google+</span></a>
                        </li>
                    </ul>
                    <hr class="intro-divider">
                    <p><spring:message code="contact.message"/>:</p>
                    <p>
                        iurii_miedviediev@epam.com
                        <br>
                        druidkuma@gmail.com
                    </p>
                    <p>+38(067)575-88-75</p>
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
