<%--
  Created by IntelliJ IDEA.
  User: Iurii_Miedviediev
  Date: 9/29/2014
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../base/headconfig.jsp" %>
    <title>${title}</title>
</head>

<body>

<%@ include file="../base/header.jsp" %>

<div class="intro-header">

    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <img src="/img/timthumb.jpg"/>
                    <h1>${title}</h1>
                    <h3>${error}</h3>
                </div>
            </div>
        </div>
    </div>

</div>

<%@ include file="../base/footer.jsp" %>

<!-- jQuery Version 1.11.0 -->
<script src="/js/jquery-1.11.0.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>
