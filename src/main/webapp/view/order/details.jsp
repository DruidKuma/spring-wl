<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Author: Iurii_Miedviediev
  Date: 9/23/2014
  Time: 1:54 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>

    <link href="/css/orderdetails.css" rel="stylesheet">
    <link href="/css/orders.css" rel="stylesheet">

    <title><spring:message code="order.view.title"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <h2><spring:message code="order.view.details"/></h2>

        <!-- List of books in the order -->
        <div id="booklist">
            <c:set var="sum" value="${0}"/>
            <c:forEach var="book" items="${order.items}">
                <c:set var="sum" value="${sum + book.price}"/>

                <div class="pricetab">
                    <h5> ${book.title} </h5>
                    <div class="price">
                        <img width="120px" height="150px" src="/book-cover/${book.id}">
                    </div>
                    <div class="infos">
                        <h4> $${book.price/100} </h4>
                        <h4> ${book.author}</h4>
                    </div>
                    <div class="pricefooter">
                        <div class="button">
                            <a href="/book?id=${book.id}"> <spring:message code="order.view.bookinfo"/> </a>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </div>
        <!-- End of books -->

        <!-- Order info and QR -->
        <table style="width: 450px;">
            <thead>
            <tr>
                <th><spring:message code="order.view.total"/></th>
                <th>$${sum/100}</th>
                <th rowspan="3" style="text-align: center">
                    <a target="_blank" href="/order/${order.id}?pdf"> <img width="100" height="100" src="/order/qr/${order.id}">
                        <br>
                        <spring:message code="order.view.download"/>
                    </a>
                </th>
            </tr>
            <tr>
                <th><spring:message code="order.view.status"/></th>
                <th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <form style="margin: 0px" action="/admin/changestatus" method="post">
                            <input type="hidden" name="id" value="${order.id}">
                            <select name="newStatus">
                                <c:choose>
                                    <c:when test="${order.status == 'WAITING'}">
                                        <option selected value="${order.status}"><spring:message code="panel.order.waiting"/></option>
                                        <option value="TAKEN"><spring:message code="panel.order.taken"/></option>
                                        <option value="RETURNED"><spring:message code="panel.order.returned"/></option>
                                    </c:when>
                                    <c:when test="${order.status == 'TAKEN'}">
                                        <option selected value="${order.status}"><spring:message code="panel.order.taken"/></option>
                                        <option value="WAITING"><spring:message code="panel.order.waiting"/></option>
                                        <option value="RETURNED"><spring:message code="panel.order.returned"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option selected value="${order.status}"><spring:message code="panel.order.returned"/></option>
                                        <option value="TAKEN"><spring:message code="panel.order.taken"/></option>
                                        <option value="WAITING"><spring:message code="panel.order.waiting"/></option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <button class="btn btn-default" type="submit">OK</button>
                        </form>
                    </sec:authorize>

                    <sec:authorize access="!hasRole('ROLE_ADMIN')">
                        <span class="orderstatus-${order.status}">${order.status}</span>
                    </sec:authorize>
                </th>
            </tr>
            <tr>
                <th><spring:message code="order.view.date"/></th>
                <th><fmt:formatDate value="${order.dateOfCreation}" pattern="dd/MM/yyyy HH:mm"/></th>
            </tr>
            </thead>
        </table>
        <!-- End of Order info -->


    </div>
    <!-- /.container -->
</div>
<!-- /.Content -->

<%@ include file="../base/footer.jsp" %>
</body>
</html>
