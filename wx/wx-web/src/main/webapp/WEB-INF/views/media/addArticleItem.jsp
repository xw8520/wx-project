<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/11/27
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>消息项编辑</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <script type="text/javascript" src="../../static/js/media/addArticleItem.js"></script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="main-title">
    </div>
    <form class="form-horizontal" role="form">
        <input type="hidden" id="id" value="${data.id}"/>
        <input id="articleId" type="hidden" value="${aid}"/>
        <br>
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">标题</label>
            <div class="col-sm-10">
                <input type="text" id="title" value="${data.title}" class="form-control" placeholder="请输入标题">
            </div>
        </div>
        <div class="form-group">
            <label for="author" class="col-sm-2 control-label">作者</label>
            <div class="col-sm-10">
                <input type="text" id="author" value="${data.author}" class="form-control" placeholder="请输入作者">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <c:if test="${data.showCover==true}">
                            <input type="checkbox" id="showCover" checked>设置为封面
                        </c:if>
                        <c:if test="${data.showCover==false}">
                            <input type="checkbox" id="showCover">设置为封面
                        </c:if>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">素材id</label>
            <div class="col-sm-10">
                <input type="text" id="mediaId" value="${data.mediaId}" class="form-control" placeholder="请输入素材id">
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">原文地址</label>
            <div class="col-sm-10">
                <input type="text" id="contentUrl" value="${data.contentUrl}" class="form-control"
                       placeholder="请输入原文地址">
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">描述</label>
            <div class="col-sm-10">
                <textarea id="digest" class="form-control" placeholder="请输入描述">${data.digest}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">内容</label>
            <div class="col-sm-10">
                <textarea id="content" rows="5" class="form-control" placeholder="请输入内容">${data.content}</textarea>
            </div>
        </div>
        <div class="form-group" style="margin-left: 40%">
            <input type="button" value="确定" onclick="add()" class="btn btn-primary btn-lg"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="取消" onclick="cancel()" class="btn btn-default btn-lg"/>
        </div>
    </form>
</div>
</body>
</html>
