<%--
  Created by IntelliJ IDEA.
  User: Iurii_Miedviediev
  Date: 9/12/2014
  Time: 12:08 PM
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Header -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/"><spring:message code="header.title"/></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="/cart"><img width="20px" height="20px" src="/img/cart.png"/></a>
                    </li>
                </sec:authorize>
                <li>
                    <a href="/about"><spring:message code="header.about"/></a>
                </li>
                <li>
                    <a href="/books"><spring:message code="header.books"/></a>
                </li>
                <li>
                    <a href="/contact"><spring:message code="header.contact"/></a>
                </li>

                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li>
                            <a href="/admin/panel"><spring:message code="header.adminpanel"/></a>
                        </li>
                    </sec:authorize>
                    <li>
                        <a href="/order"><spring:message code="header.myorders"/></a>
                    </li>
                    <li>
                        <a href="/j_spring_security_logout"><spring:message code="header.logout"/></a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAnonymous()">
                    <li>
                        <a href="/login"><spring:message code="header.signin"/></a>
                    </li>
                    <li>
                        <a href="/register"><spring:message code="header.signup"/></a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
