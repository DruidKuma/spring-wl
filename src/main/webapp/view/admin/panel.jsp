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
    <%@ include file="../base/headconfig.jsp" %>

    <!-- Custom CSS -->
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">

    <title><spring:message code="header.adminpanel"/></title>
</head>
<body>

<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <div class="intro-message">

            <div class="btn-group">
                <a href="#" onclick="if ($('.usertab').css('display')=='none'){$('.usertab, .ordertab').toggle();};">
                    <button type="button" class="btn btn-default">
                        <spring:message code="panel.users"/>
                    </button>
                </a>
                <a href="#" onclick="if ($('.ordertab').css('display')=='none'){$('.usertab, .ordertab').toggle();};">
                    <button type="button" class="btn btn-default">
                        <spring:message code="panel.orders"/>
                    </button>
                </a>
            </div>

            <div id="Content">

                <!-- Tab with users -->
                <div class="usertab">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered"
                               id="dataTables-example">
                            <thead>
                            <tr>
                                <th><spring:message code="panel.login"/></th>
                                <th><spring:message code="panel.name"/></th>
                                <th><spring:message code="panel.surname"/></th>
                                <th><spring:message code="panel.email"/></th>
                                <th><spring:message code="panel.activeorders"/></th>
                                <th><spring:message code="panel.role"/></th>
                                <th><spring:message code="panel.discount"/></th>
                                <th><spring:message code="panel.remove"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td align="center"><strong style="font-size: 16px;">${user.credentials.login}</strong></td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.email}</td>
                                    <td>${fn:length(user.orders)}</td>
                                    <td>
                                        <form style="margin-top: 10px" method="post" action="/admin/user?role">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <select name="newRole">
                                                <c:choose>
                                                    <c:when test="${user.credentials.role == 'ROLE_USER'}">
                                                        <option selected value="ROLE_USER"><spring:message code="panel.simpleuser"/></option>
                                                        <option value="ROLE_ADMIN"><spring:message code="panel.administrator"/></option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="ROLE_USER"><spring:message code="panel.simpleuser"/></option>
                                                        <option selected value="ROLE_ADMIN"><spring:message code="panel.administrator"/></option>
                                                    </c:otherwise>
                                                </c:choose>

                                            </select>
                                            <input type="submit" value="OK">
                                        </form>
                                    </td>
                                    <td>
                                        <form style="margin-top: 10px" method="post" action="/admin/user?discount">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <input style="width: 40px" type="text" name="newDiscount"
                                                   placeholder="<fmt:parseNumber integerOnly="true"
                                               type="number" value="${user.discount*100}"/>%">
                                            <input type="submit" value="OK">
                                        </form>
                                    </td>
                                    <td align="center">
                                        <!-- Button trigger modal -->
                                        <a href="#"><span class="glyphicon glyphicon-remove" data-toggle="modal" data-target="#myModal${user.id}"/></a>
                                    </td>

                                    <div class="modal fade" id="myModal${user.id}">
                                        <div class="modal-dialog">
                                            <div class="modal-content" style="color: #000000">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                                    <h4 class="modal-title">Delete Confirmation</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <p>This action will delete the user and all his/her orders. Are you sure you want to delete this information?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                                    <button type="button" class="btn btn-primary"><a style="color: #ffffff; text-decoration: none" href="/admin/user?remove&id=${user.id}">Delete</a></button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div><!-- /.modal -->

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- End of User tab -->

                <!-- Tab with orders -->
                <div class="ordertab">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered"
                               id="dataTables-example2">
                            <thead>
                            <tr>
                                <th><spring:message code="panel.order.details"/></th>
                                <th><spring:message code="panel.order.user"/></th>
                                <th><spring:message code="panel.order.books"/></th>
                                <th><spring:message code="panel.order.date"/></th>
                                <th><spring:message code="panel.order.price"/></th>
                                <th><spring:message code="panel.order.status"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <c:set var="books" value="${order.items}"/>

                                    <!-- Finding total price of the order -->
                                    <c:set var="sum" value="${0}"/>
                                    <c:forEach var="book" items="${books}">
                                        <c:set var="sum" value="${sum + book.price}"/>
                                    </c:forEach>

                                    <td align="center"><a href="/order/${order.id}"><strong style="font-size: 16px;"><spring:message code="panel.order.view"/></strong></a></td>
                                    <td>${order.user.credentials.login}</td>
                                    <td>${fn:length(books)}</td>
                                    <td><fmt:formatDate value="${order.dateOfCreation}" pattern="dd/MM/yyyy - HH:mm"/></td>
                                    <td>$${sum/100}</td>
                                    <td style="width: 170px">
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
                                            <input type="submit" value="OK">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- End of Order tab -->

            </div>
        </div>
    </div>
    <!-- /.container -->
</div>

<!-- jQuery Version 1.11.0 -->
<script src="/js/jquery-1.11.0.js"></script>

<!-- Bootstrap Javascript -->
<script src="/bootstrap/js/bootstrap.min.js"></script>

<!-- DataTables JavaScript -->
<script src="/js/jquery.dataTables.js"></script>
<script src="/js/dataTables.bootstrap.js"></script>

<!-- script to make datatables work and hide one of the tabs -->
<script type="text/javascript">
    $(document).ready(
            function(){
                $('.ordertab').hide();
            });

    $(document).ready(
            function(){
                $('#dataTables-example').dataTable();
                $('#dataTables-example2').dataTable();
            });
</script>

<%@ include file="../base/footer.jsp" %>
</body>

</html>

