<%--
  Author: Iurii_Miedviediev
  Date: 9/12/2014
  Time: 12:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<body>
<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <ul class="list-inline">
                    <li>
                        <a href="/"><spring:message code="footer.home"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/about"><spring:message code="header.about"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/books"><spring:message code="header.books"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/contact"><spring:message code="header.contact"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/technologies"><spring:message code="footer.usedtechs"/></a>
                    </li>
                </ul>
                <p class="copyright text-muted small"><spring:message code="footer.copyright"/></p>
                <p>
                <ul class="list-inline">
                    <li>
                        <a href="/changelang?lang=en"><img src="/img/i18n/english.jpg"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/changelang?lang=ua"><img src="/img/i18n/ukrainian.png"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/changelang?lang=de"><img src="/img/i18n/german.jpg"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/changelang?lang=jp"><img src="/img/i18n/japan.png"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="/changelang?lang=ru"><img src="/img/i18n/russian.png"/></a>
                    </li>
                </ul>
                </p>
            </div>

            <!-- Search section -->
            <div class="col-sm-3 col-md-3 pull-right">
                <form action="/books">
                    <div class="input-append" style="margin-bottom: 15px;">
                        <input style="float: left; width: 85%; height: 34px" type="text" placeholder="Search" class="span2 search-query" name="search">
                        <div style="float: left" class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                    <select style="margin-top: 15px; width: 60%" class="form-control" name="criteria">
                        <option value="author"><spring:message code="search.byauthor"/></option>
                        <option value="title"><spring:message code="search.bytitle"/></option>
                    </select>
                </form>
            </div>
            <!-- End of search section -->

        </div>
    </div>
</footer>
</body>
</html>
