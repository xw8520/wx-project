<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2016/2/17
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>首页</title>
</head>
<body>
<img src="${requestScope.code}"/>
获取个人信息：${userInfo.getNickname()}<br/>
获取多人信息：
<c:forEach var="item" items="${list}">
    <span>${item.getNickname()}</span><br/>
</c:forEach>

<a href="share.html">微信分享页面</a>
</body>
</html>
