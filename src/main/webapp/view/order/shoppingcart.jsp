<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Author: Iurii_Miedviediev
  Date: 9/17/2014
  Time: 10:14 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Custom CSS -->
    <link href="/css/orders.css" rel="stylesheet">
    <link href="/css/cart.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">

    <%@ include file="../base/headconfig.jsp" %>

    <title><spring:message code="cart.title"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <div class="intro-message">

            <!-- Errors block -->
            <c:if test="${(not empty error) || (not empty maxTakenError) || (not empty unavailable)}">
                <div style="width: 310px; margin: auto" class="errorblock">
                        ${error}
                        ${maxTakenError}
                        ${unavailable}
                </div>
            </c:if>
            <!-- End of errors block -->

            <c:choose>
                <c:when test="${fn:length(cart) <= 0}">
                    <h3><spring:message code="cart.empty"/></h3>
                </c:when>
                <c:otherwise>

                    <h3><spring:message code="cart.heading"/></h3>
                    <table>
                        <thead>
                        <tr>
                            <th><spring:message code="cart.booktitle"/></th>
                            <th><spring:message code="cart.price"/></th>
                            <th><spring:message code="cart.remove"/></th>
                        </tr>
                        </thead>
                        <c:forEach var="book" items="${cart}">
                            <tr>
                                <td><strong>${book.title}</strong></td>
                                <td>$${book.price/100}</td>
                                <td align="center"><a href="cart?remove&id=${book.id}"><span class="glyphicon glyphicon-remove"></span></a></td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div class="checkout-block pull-right">
                        <div class="_column subtotal" id="subtotalCtr">
                            <div class="cart-totals-key"><spring:message code="cart.total"/></div>
                            <div class="cart-totals-value">$${total/100}</div>
                        </div>

                        <div class="_column total" id="totalCtr">
                            <div class="cart-totals-key discount-text"><spring:message code="cart.discount"/></div>
                            <div class="cart-totals-value discount-text">$${discount/100}</div>
                        </div>

                        <!-- Payment button -->
                        <form action="/order" method="POST">
                            <script
                                    src="https://checkout.stripe.com/checkout.js" class="stripe-button"
                                    data-key="pk_test_gFnfwTOXgxNDD1VyjsiFQUzS"
                                    data-image="/img/library.png"
                                    data-name="Pay for Rent"
                                    data-description="$${discount/100}"
                                    data-amount="${discount}">
                            </script>
                        </form>
                        <!-- End of payment Button -->
                    </div>

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
