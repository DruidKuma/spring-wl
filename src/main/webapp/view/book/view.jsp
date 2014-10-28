<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>

    <!-- Custom CSS -->
    <link href="css/book.css" rel="stylesheet">

    <title>${book.title}</title>
</head>
<body>
<%@ include file="../base/header.jsp" %>

<div class="intro-header">
    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="row">

                <div class="col-md-4">
                    <img class="img-responsive" src="/book-cover/${book.id}">
                </div>

                <div class="col-md-6">
                    <h3><c:out value="${book.title}"/></h3>
                    <p><c:out value="${book.description}"/></p>
                    <h4><c:out value="${book.author}"/>, <c:out value="${book.year}"/></h4>
                    <h5><c:out value="$${book.price/100}"/></h5>

                    <div class="ratings">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <p class="pull-right"><a href="/admin/book?edit&id=${book.id}" class="btn btn-default btn-lg"> <span class="network-name"><spring:message code="book.edit.heading"/></span></a></p>
                        </sec:authorize>

                        <sec:authorize access="isAuthenticated()">
                            <c:if test="${available}">
                                <p class="pull-right order-button"><a href="/cart?create&id=${book.id}" class="btn btn-default btn-lg"> <span class="network-name"><spring:message code="book.view.order"/></span></a></p>
                            </c:if>
                        </sec:authorize>

                        <p class="pull-left">
                            <a href="/books" class="btn btn-default btn-lg"> <span class="network-name"><spring:message code="book.add.backtolist"/></span></a>
                        </p>
                    </div>
                </div>

            </div>
            <!-- /.row -->

        </div>

    </div>
    <!-- /.container -->
</div>
<%@ include file="../base/footer.jsp" %>
</body>
</html>