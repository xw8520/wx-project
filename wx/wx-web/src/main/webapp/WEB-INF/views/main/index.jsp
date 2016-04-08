<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../static/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../../static/css/all.css" type="text/css"/>
    <link rel="stylesheet" href="../../static/css/main.css" type="text/css"/>
    <script type="text/javascript" src="../../static/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/main.js"></script>
</head>
<body>
<div class="header navbar navbar-fixed-top">
    <img src="../../static/image/logo-v2.png">
</div>
<div class="navbar-left">
    <ul>
        <c:forEach items="${menu}" var="item">
            <li>
                <a>${item.getName()}
                    <c:if test="${item.getChild().size()>0}">
                        <i class="fa fa-angle-left pull-right"></i>
                    </c:if>
                </a>
                <c:if test="${item.getChild().size()>0}">
                    <ul class="nav-child">
                        <c:forEach items="${item.getChild()}" var="child">
                            <li>
                                <a href="javascript:void(0)" url="${child.getUrl()}">
                                        ${child.getName()}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </li>
        </c:forEach>
    </ul>
</div>

<div class="content-wrapper">
    <iframe id="frameMain" class="fram-main">

    </iframe>
</div>
</body>
</html>
