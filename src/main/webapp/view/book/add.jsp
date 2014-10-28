<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <%@ include file="../base/headconfig.jsp" %>
    <link href="/css/signin.css" rel="stylesheet">
    <title><spring:message code="book.add.heading"/></title>
</head>
<body>
<%@ include file="../base/header.jsp" %>
<div class="intro-header">

    <div class="container">

        <div class="intro-message">

            <h2 class="form-signin-heading"><spring:message code="book.add.heading"/>:</h2>

            <!-- Messages -->
            <spring:message code="book.add.title" var="bookTitle"/>
            <spring:message code="book.add.author" var="bookAuthor"/>
            <spring:message code="book.add.year" var="bookYear"/>
            <spring:message code="book.add.price" var="bookPrice"/>
            <spring:message code="book.add.description" var="bookDescription"/>

            <form:form method="POST" class="form-book" modelAttribute="bookForm" action="/admin/book?add" enctype="multipart/form-data">
                <form:errors path="*" cssClass="errorblock" element="div" />
                <form:input cssClass="form-control" path="book.title" placeholder="${bookTitle}"/>
                <form:input cssClass="form-control" path="book.author" placeholder="${bookAuthor}" />
                <form:input cssClass="form-control" path="book.year" placeholder="${bookYear}" />
                <form:select cssClass="form-control" cssStyle="margin-bottom: 5px" path="book.genre">
                    <c:forEach var="genre" items="${genres}">
                        <form:option value="${genre}"><spring:message code="genre.${genre}"/></form:option>
                    </c:forEach>
                </form:select>
                <form:textarea rows="10" cssClass="form-control" path="book.description" placeholder="${bookDescription}"/>
                <form:input cssClass="form-control" path="book.price" placeholder="${bookPrice}"/>
                <form:input path="cover" type="file" class="filestyle form-control" data-badge="false"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="book.add.submit"/></button>
            </form:form>
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