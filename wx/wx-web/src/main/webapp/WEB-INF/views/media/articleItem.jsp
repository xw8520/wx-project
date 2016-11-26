<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/11/26
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>素材项管理</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <script type="text/javascript" src="../../static/js/media/articleItem.js"></script>
    <script type="text/x-jquery-tmpl" id="tempBody">
        <tr>
            <td><input class="chkId" val="{{= id}}" type="checkbox"></td>
            <td>{{= id}}</td>
            <td>{{= title}}</td>
             <td>{{= type}}</td>
            <td>{{= account}}</td>
            <td>{{= remark}}</td>
        </tr>
    </script>
</head>
<body>

</body>
</html>
