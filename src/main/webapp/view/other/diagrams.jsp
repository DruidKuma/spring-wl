<%--
  Author: DruidKuma
  Date: 10/3/14
  Time: 10:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><spring:message code="diagram.title"/></title>
    <script type="text/javascript" src="/yoxview/yoxview-init.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="/yoxview/jquery.yoxview-2.21.min.js"></script>

    <%@ include file="../base/headconfig.jsp" %>
</head>
<body>
<%@ include file="../base/header.jsp" %>
<div class="intro-header">
    <div id="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h2>The diagrams of Web-Library Project:</h2>
                    <div class="center-block" style="width: 450px">
                    <!-- This is how your thumbnails should look like: -->
                    <div class="thumbnails yoxview">
                        <a href="/img/diagrams/mindmap.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/mindmap.png"
                                 alt="MindMap"
                                 title="Mind Map Diagram"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/order-bp.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/order-bp.png"
                                 alt="Order Business Process"
                                 title="Book Order Business Process"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/receive-bp.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/receive-bp.png"
                                 alt="Receive Business Process"
                                 title="Book Receive Business Process"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/return-bp.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/return-bp.png"
                                 alt="Return Business Process"
                                 title="Book Return Business Process"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/mockup1.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/mockup1.png"
                                 alt="Mockups Part 1"
                                 title="Mockups Part 1"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/mockup2.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/mockup2.png"
                                 alt="Mockups Part 2"
                                 title="Mockups Part 2"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/usecase.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/usecase.png"
                                 alt="Use Cases"
                                 title="Use Case Diagram"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/er.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/er.png"
                                 alt="ER Diagram"
                                 title="Entity-Relation Diagram"
                                 style="margin: 0 5px 10px 0"/>
                        </a>

                        <a href="/img/diagrams/db-diagram.png">
                            <img width="100px" height="100px"
                                 src="/img/diagrams/db-diagram.png"
                                 alt="Database Diagram"
                                 title="Database Diagram"
                                 style="margin: 0 5px 10px 0"/>
                        </a>
                    </div>
                    <!-- The thumbnails end here -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../base/footer.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){
        $(".yoxview").yoxview();
    });
</script>
</body>
</html>
