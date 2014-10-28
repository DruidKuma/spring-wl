<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>
    <link href="/css/signin.css" rel="stylesheet">
    <title><spring:message code="book.edit.heading"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>

<div class="intro-header">

    <div class="container">

        <div class="intro-message">

            <h2 class="form-signin-heading"><spring:message code="book.edit.heading"/>:</h2>

            <form:form method="POST" class="form-book" modelAttribute="bookForm" action="/admin/book?edit" enctype="multipart/form-data">
                <form:errors path="*" cssClass="errorblock" element="div" />
                <form:input type="hidden" path="book.id" value="${book.id}"/>
                <form:input cssClass="form-control" path="book.title" value="${fn:escapeXml(book.title)}"/>
                <form:input cssClass="form-control" path="book.author" value="${fn:escapeXml(book.author)}"/>
                <form:input cssClass="form-control" path="book.year" value="${fn:escapeXml(book.year)}"/>
                <form:select cssClass="form-control" cssStyle="margin-bottom: 5px" path="book.genre">
                    <c:forEach var="genre" items="${genres}">
                        <form:option value="${genre}">${genre}</form:option>
                    </c:forEach>
                </form:select>
                <form:textarea rows="10" cssClass="form-control" path="book.description" value="${fn:escapeXml(book.description)}"/>
                <form:input cssClass="form-control" path="book.price" value="${fn:escapeXml(book.price)}"/>
                <form:input path="cover" type="file" class="filestyle form-control" data-badge="false"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="book.edit.submit"/></button>
            </form:form>

            <form action="/admin/book?delete" class="form-book" method="post">
                <input type="hidden" name="id" value="${bookForm.book.id}">
                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="book.edit.delete"/></button>
            </form>

            <a href="/books"><spring:message code="book.add.backtolist"/></a>

        </div>

    </div>
</div>

<%@ include file="../base/footer.jsp" %>

<!-- jQuery Version 1.11.0 -->
<script src="/js/jquery-1.11.0.js"></script>

<!-- Core JavaScript -->
<script src="/js/inputfile.js"></script>
</body>
</html>