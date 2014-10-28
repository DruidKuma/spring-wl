<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>

    <!-- Custom CSS -->
    <link href="/css/bookcatalog.css" rel="stylesheet">

    <title><spring:message code="book.list.title"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>

<!-- Pagination URLs -->
<c:url var="firstUrl" value="/books?criteria=${criteria}&search=${search}&genre=${jenre}&page=1" />
<c:url var="lastUrl" value="/books?criteria=${criteria}&search=${search}&genre=${jenre}&page=${books.totalPages}" />
<c:url var="prevUrl" value="/books?criteria=${criteria}&search=${search}&genre=${jenre}&page=${currentIndex - 1}" />
<c:url var="nextUrl" value="/books?criteria=${criteria}&search=${search}&genre=${jenre}&page=${currentIndex + 1}" />


<div class="intro-header">

    <div class="container">

        <div class="row">

            <div class="col-md-3">
                <p class="lead"><spring:message code="book.list.genres"/></p>
                <div class="list-group">
                    <c:forEach var="genre" items="${genres}">
                        <a href="/books?genre=${genre}" class="list-group-item"><spring:message code="genre.${genre}"/></a>
                    </c:forEach>
                </div>
            </div>

            <div class="col-md-9">

                <!-- Carousel -->
                <div class="row carousel-holder">
                    <div class="col-md-12">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img class="slide-image" src="http://www.electric-type.com/wordpress/wp-content/uploads/2011/02/Screen-shot-2011-02-24-at-10.54.33-AM-800x300.png" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://www.electric-type.com/wordpress/wp-content/uploads/2011/10/whybook-800x300.png" alt="">
                                </div>
                                <div class="item">
                                    <img class="slide-image" src="http://www.vfxmonkey.com/wp-content/uploads/2011/01/hp6-e1295508403239-800x300.jpg" alt="">
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
                <!-- End of Carousel -->

                <!-- Books rendering -->
                <div class="row">
                    <c:forEach var="book" items="${contents}">
                        <div class="col-sm-4 col-lg-4 col-md-4 book-item">
                            <div class="thumbnail">
                                <h4><a href="/book?id=${book.id}"><c:out value="${book.title}"/></a></h4>
                                <div class="caption">
                                    <p><c:out value="${book.description}"/></p>
                                </div>
                                <div class="ratings">
                                    <p class="pull-right"><c:out value="${book.author}"/></p>
                                    <p>
                                        <span class="glyphicon">$${book.price/100}</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- End of book rendering -->

                <!-- Pagination Stuff -->
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${currentIndex == 1}">
                            <li class="disabled"><a href="#">&lt;&lt;</a></li>
                            <li class="disabled"><a href="#">&lt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${firstUrl}">&lt;&lt;</a></li>
                            <li><a href="${prevUrl}">&lt;</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">

                        <c:url var="pageUrl" value="/books?criteria=${criteria}&search=${search}&genre=${jenre}&page=${i}"/>

                        <c:choose>
                            <c:when test="${i == currentIndex}">
                                <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${currentIndex == books.totalPages}">
                            <li class="disabled"><a href="#">&gt;</a></li>
                            <li class="disabled"><a href="#">&gt;&gt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${nextUrl}">&gt;</a></li>
                            <li><a href="${lastUrl}">&gt;&gt;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <!-- End of pagination -->

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p><a href="/admin/book?add" class="btn btn-default btn-lg"> <span class="network-name"><spring:message code="book.add.heading"/></span></a></p>
                </sec:authorize>

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