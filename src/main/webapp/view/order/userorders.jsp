<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Author: Iurii_Miedviediev
  Date: 9/18/2014
  Time: 12:32 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <!-- Custom CSS -->
    <link href="/css/orders.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">

    <%@ include file="../base/headconfig.jsp" %>
    <title><spring:message code="userorders.title"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <div class="intro-message">

            <c:choose>
                <c:when test="${fn:length(orders) <= 0}">
                    <h3><spring:message code="userorders.empty"/></h3>
                </c:when>
                <c:otherwise>

                    <h3><spring:message code="userorders.ordered"/>:</h3>
                    <table>
                        <thead>
                        <tr>
                            <th><spring:message code="userorders.details"/></th>
                            <th><spring:message code="userorders.books"/></th>
                            <th><spring:message code="userorders.date"/></th>
                            <th><spring:message code="userorders.price"/></th>
                            <th><spring:message code="userorders.status"/></th>
                        </tr>
                        </thead>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <c:set var="books" value="${order.items}"/>

                                <!-- Finding total price of the order -->
                                <c:set var="sum" value="${0}"/>
                                <c:forEach var="book" items="${books}">
                                    <c:set var="sum" value="${sum + book.price}"/>
                                </c:forEach>

                                <td align="center"><a href="/order/${order.id}"><strong><spring:message code="userorders.viewdetails"/></strong></a></td>
                                <td>${fn:length(books)}</td>
                                <td><fmt:formatDate value="${order.dateOfCreation}" pattern="dd/MM/yyyy - HH:mm"/></td>
                                <td>$${sum/100}</td>
                                <td><span class="orderstatus-${order.status}"><spring:message code="panel.order.${order.status}"/></span></td>
                            </tr>
                        </c:forEach>
                    </table>

                </c:otherwise>
            </c:choose>

        </div>

    </div>
    <!-- /.container -->
</div>
<!-- /.Content -->

<%@ include file="../base/footer.jsp" %>
</body>
</html>
