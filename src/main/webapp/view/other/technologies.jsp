<%--
  Author: Iurii_Miedviediev
  Date: 10/3/2014
  Time: 2:18 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light' rel='stylesheet' type='text/css'>
    <link href="/css/technologies.css" rel="stylesheet">
    <%@ include file="../base/headconfig.jsp" %>
    <title><spring:message code="tech.title"/></title>
</head>

<body>

<%@ include file="../base/header.jsp" %>

<!-- Content -->
<div class="intro-header">

    <div class="container">

        <div class="row">
            <h1><spring:message code="tech.heading"/>:</h1>
            <table>
                <thead>
                <tr>
                    <th><spring:message code="tech.tech"/></th>
                    <th><spring:message code="tech.description"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><img src="/img/about/apache-commons.png"/></td>
                    <td>
                        <h3>Apache Commons Libraries</h3>
                        <p>
                            This library was used in my project to customize the file upload button and
                            to work with received images in a comfortable way. Also I have thought about
                            hashing passwords with their encoder until I've got my hands to Spring Security
                            built-in BCryptEncoder, which fully suits my needs.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/apache-tomcat.png"/></td>
                    <td>
                        <h3>Apache Tomcat 7</h3>
                        <p>
                            Great open-source software implementation of the Servlet and JSP technologies.
                            OpenShift platform gave me a choice of many containers, and I've chosen this one
                            to deploy on.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/bonecp.jpg"/></td>
                    <td>
                        <h3>BoneCP Connection Pool</h3>
                        <p>
                            Piece of cake for developers of concurrent web-technologies. Great support,
                            nice documentation and lots of useful examples with most known databases.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/bootstrap.jpg"/></td>
                    <td>
                        <h3>Twitter Bootstrap 3</h3>
                        <p>
                            A huge pack of pre-written styles, animations and pages' elements.
                            Software miracle both for back-end and front-end developers. Makes your pages
                            nice and as beautiful as I have :) One even doesn't need to know a lot of css/js staff
                            to begin using this great style maker
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/hibernate.png"/></td>
                    <td>
                        <h3>Hibernate ORM Tool (v.4.3.6)</h3>
                        <p>
                            One of the most famous ORM tools in Java programming.
                            I've chosen one of the latest versions. Though it is awful related to
                            optimization concerns, writing code with Hibernate is just a pleasure.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/hibernate-validator.png"/></td>
                    <td>
                        <h3>Hibernate Validator (v.4.3.0)</h3>
                        <p>
                            Of course, nobody would check your inputs better than yourself, but Hibernate
                            Validator does it in such a clean and cosy way, one would really want it in his or her
                            project. So did I :).
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/hsql.png"/></td>
                    <td>
                        <h3>Hyper SQL (v.2.2.8)</h3>
                        <p>
                            Great in-memory database! I have used it at the beginning of my project until
                            I began thinking about deploying my library to the server. But even after that
                            in-memory database is still present in my project, in unit-tests.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/html-css.jpg"/></td>
                    <td>
                        <h3>HTML5/CSS3</h3>
                        <p>
                            Well, that's, maybe, I shouldn't even add to this list.
                            Everything is clear even without words. I haven't used HTML5 canvas, but used
                            elementary new tags, like footer, header and nav, and also CSS3 transitions.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/itext.png"/></td>
                    <td>
                        <h3>iText</h3>
                        <p>
                            Very powerful library for working with PDF documents. In best practices it can
                            generate dynamic editable PDF documents. There is a huge book consisting of over 900
                            pages written about only this library ('iText in action'). But for my project I have
                            just covered the peak of the iceberg titled iText.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/javascript.png"/></td>
                    <td>
                        <h3>JavaScript</h3>
                        <p>
                            No one modern web-site can deal without javascript. So does mine. Checkout window,
                            beautiful tables and much more was done with the help of JavaScript.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/jetty.png"/></td>
                    <td>
                        <h3>Jetty Container</h3>
                        <p>
                            Beautiful tiny container I have used when I had started the project. On deployment
                            moment I've shifted to Tomcat, but when I start the project on local machine, I
                            still use Jetty. It is built-in as maven plugin, so one could just start the whole
                            application via 'mvn jetty:run' without any previous configurations.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/jpa.png"/></td>
                    <td>
                        <h3>JPA</h3>
                        <p>
                            Who knows, maybe I'll decide to move to another DB in the future. With the help of
                            JPA I can do it, just by changing a few lines of code. Besides base JPA, I've also
                            used JPA model generator to generate meta models for Specifications on the fly.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/jquery.png"/></td>
                    <td>
                        <h3>jQuery (v.1.11.0)</h3>
                        <p>
                            The most famous JavaScript library. If not jQuery, many features would be much harder
                            to implement. The feature that impressed me most of all while implementing this project
                            is representing many tables at once, but hiding all of them except for one (active)
                            and switching between them via self-implemented tabs.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/jquery-datable.jpg"/></td>
                    <td>
                        <h3>jQuery DataTable</h3>
                        <p>
                            A small jQuery plugin that allowed me to style my tables and make them fully functional
                            with sorting, searching, paging and rendering. Not very optimal from the back-end
                            view, but everything 'from the box' at the front-end.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/jsp.jpg"/></td>
                    <td>
                        <h3>JSP</h3>
                        <p>
                            A great Java technology that produce dynamically generated web pages. In my
                            project it is based on HTML, though it can also be based on XML or other document
                            types.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/jstl.png"/></td>
                    <td>
                        <h3>JSTL (v.1.2)</h3>
                        <p>
                            Java Server Pages tag library that allowed me to loop over my rows in tables,
                            over lists of books, just like in programming languages, insert if clauses,
                            format date, convert numbers et cetera, et cetera, et cetera...
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/junit.gif"/></td>
                    <td>
                        <h3>JUnit (v.4.11)</h3>
                        <p>
                            A powerful testing framework. All tests in my project are made via JUnit.
                            I have configured it first of all and really wanted to follow the TDD pattern,
                            building my project, by stars played bad game with this idea :)
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/hamcrest.jpg"/></td>
                    <td>
                        <h3>Hamcrest (v.1.3)</h3>
                        <p>
                            Provides a library of matcher objects (also known as constraints or predicates)
                            allowing 'match' rules to be defined declaratively, to be used in other frameworks.
                            Typical scenarios include testing frameworks, mocking libraries and UI validation rules.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/spring-test.png"/></td>
                    <td>
                        <h3>Spring Test (v.3.2.3)</h3>
                        <p>
                            The Spring Framework provides first-class support for integration testing in the spring-test module.
                            I tried to use every single power of Spring framework and its components, so Spring testing
                            framework helped me a lot.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/dbunit.png"/></td>
                    <td>
                        <h3>DBUnit (v.2.4.9)</h3>
                        <p>
                            DbUnit is a JUnit extension targeted at database-driven projects that, among other things,
                            puts your database into a known state between test runs. This is an excellent way to
                            avoid the myriad of problems that can occur when one test case corrupts the database
                            and causes subsequent tests to fail or exacerbate the damage.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/lombok.jpg"/></td>
                    <td>
                        <h3>Lombok</h3>
                        <p>
                            This is a syntactic sugar for Java developers as it deletes most of the boilerplate
                            code from your classes. Getters, setters, constructors, hashCode() and equals(),
                            try-catch blocks and much more - all is unnecessary now thanks to Lombok!
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/maven.png"/></td>
                    <td>
                        <h3>Maven</h3>
                        <p>
                            Of course, one of the main skills in Java programming. The best build tool ever.
                            Used all over the world on numerous projects. Maven makes your life as a developer
                            a lot easier
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/mockito.png"/></td>
                    <td>
                        <h3>Mockito</h3>
                        <p>
                            Mockito helped me to organize the architecture of my tests. With the help of its mocks
                            I was sure that the problem is indeed in the class I'm testing while implementing
                            integration tests.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/postgresql.png"/></td>
                    <td>
                        <h3>PostgreSQL (v.9.2)</h3>
                        <p>
                            Very nice choice both for large production projects and for such small toy-projects as
                            mine. Very close to SQL standard with quite a lot of additional functions and features.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/openshift.png"/></td>
                    <td>
                        <h3>OpenShift</h3>
                        <p>
                            Free deployment server with a number of options in choice of databases, containers
                            etc. Provides with a free space of 1GB for your application, including all sources,
                            resources and database data.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/qrgen.jpg"/></td>
                    <td>
                        <h3>QRGen</h3>
                        <p>
                            Creates a layer over ZXing barcode library which makes work with QR codes just a piece
                            of cake. Makes use of Builder pattern to make all necessary configuration to
                            your QR codes.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/slf4j.jpg"/></td>
                    <td>
                        <h3>SLF4J (v.1.5.10)</h3>
                        <p>
                            The most popular logging framework and programming technology in Java programming
                            regarding to Google analysis of 10 thousands projects created in 2014. Nothing special,
                            just logging.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/spring-core.png"/></td>
                    <td>
                        <h3>Spring Core (v.4.0.6)</h3>
                        <p>
                            One of the major tools to implement this project. One of the most necessary tools in
                            developer's gentleman case. A great framework based on Java Beans.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/spring-data.jpg"/></td>
                    <td>
                        <h3>Spring Data (v.1.7.0)</h3>
                        <p>
                            You receive a lot of 'tasty' features just from the box with another great Spring
                            project. With the help of Spring Data I can, for example, automatically convert
                            my request parameters to the necessary type without implicitly writing it in the code.
                            It gently would use your repositories to automatically pull out your entities from the
                            DB just passing an id param in the request, for example.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/spring-mvc.png"/></td>
                    <td>
                        <h3>Spring MVC (v.4.0.6)</h3>
                        <p>
                            Another layer over Spring Core. No more simple Servlets, checked exceptions
                            and lots of boilerplate code with Spring. Lots of choices of configurations.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/spring-security.png"/></td>
                    <td>
                        <h3>Spring Security (v.3.2.3)</h3>
                        <p>
                            Authorization and security is one of the most important sides of any web-application.
                            Spring security simplifies work with it, providing with authorization features,
                            remember-me token, secure all necessary pages for wanted roles.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/spring-security-taglib.png"/></td>
                    <td>
                        <h3>Spring Security Taglib (v.3.2.3)</h3>
                        <p>
                            A small library for Spring Security which provides you with tags to use in your
                            Java Server Pages for representing different content for different user types.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/stripe.gif"/></td>
                    <td>
                        <h3>Stripe Payment System</h3>
                        <p>
                            A powerful payment system with integration to most common programming languages.
                            Great documentation and lots of configuration options. Free to use for testing,
                            but unprofitable in use as it takes 2.5% plus 0.6$ from every transaction.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/zxing.jpg"/></td>
                    <td>
                        <h3>ZXing</h3>
                        <p>
                            A powerful library for working with bar-codes. I needed only QR support from it,
                            but looked through its documentation and liked this technology very much. I think we'll
                            meet again soon :)
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/yoxview.gif"/></td>
                    <td>
                        <h3>YoxView</h3>
                        <p>
                            This wonderful jQuery plugin makes life easiear when case is in watching images or video on
                            your website or implementing a sort of gallery. With the help of it diagrams of my project
                            (available for admin only) look better than ever :)
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/balsamiq.jpg"/></td>
                    <td>
                        <h3>Balsamiq Mockups</h3>
                        <p>
                            At the very beginning of the project I have used this wonderful tool, creating and thinking
                            over possible user stories, mockups and everything about how I would like to see my project
                            in a ready state. I've done mockups, based on iPhone mobile screen, but then decided to
                            implement full desktop version of the site.
                        </p>
                    </td>
                </tr>

                <tr>
                    <td><img src="/img/about/lucidchart.png"/></td>
                    <td>
                        <h3>Lucidchart</h3>
                        <p>
                            Source of all my diagrams based on the current project, is Lucidchart. Very powerful,
                            beautiful and easy to use tool for cretind mindmaps, ER diagrams, Business Processes,
                            Use Cases, etc. I tried to use it as fully as I could, creating my project's diagrams.
                        </p>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

    </div>
    <!-- /.container -->

</div>
<!-- /.Content -->

<%@ include file="../base/footer.jsp" %>

<!-- jQuery Version 1.11.0 -->
<script src="/js/jquery-1.11.0.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>