<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/18
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>OpenId群发</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <link rel="stylesheet" href="../../static/js/chosen/chosen.min.css"/>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <script type="text/javascript" src="../../static/js/message/sendByOpenId.js"></script>
    <script type="text/javascript" src="../../static/js/chosen/chosen.jquery.min.js"></script>
    <script type="text/x-jquery-tmpl" id="tempSelc">
        <option value="{{= id}}">{{= name}}</option>
    </script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="form-horizontal" id="formMedia">
        <div class="form-group">
            <label class="col-sm-2 control-label" for="mid">消息Id:</label>
            <div class="col-sm-9">
                <input type="hidden" id="id" value="${sendByOpenIdInfo.id}"/>
                <input type="text" id="mid" class="form-control" value="${sendByOpenIdInfo.mid}" readonly/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="title">标题:</label>
            <div class="col-sm-9">
                <input type="text" id="title" value="${sendByOpenIdInfo.title}" class="form-control" maxlength="50"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <c:if test="${sendByOpenIdInfo.toall}">
                            <input type="checkbox" id="toall" checked="checked" />发送全部
                        </c:if>
                        <c:if test="${!sendByOpenIdInfo.toall}">
                            <input type="checkbox" id="toall"/>发送全部
                        </c:if>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="accountId">公众号:</label>
            <div class="col-sm-9">
                <input id="accountId" type="hidden" value="${sendByOpenIdInfo.accountId}" class="form-control"/>
                <input type="text" id="accountName" value="${sendByOpenIdInfo.accountName}" class="form-control"
                       readonly/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="remark">OpenId:</label>
            <div class="col-sm-9">
                <textarea id="openId" class="form-control">${sendByOpenIdInfo.openId}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="remark">备注:</label>
            <div class="col-sm-9">
                <textarea id="remark" class="form-control">${sendByOpenIdInfo.remark}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-9" style="text-align: center">
                <input type="button" id="btnSave" class="btn btn-default" value="保存" onclick="add()"/>
                <input type="button" id="btnCancel" class="btn btn-default" value="取消" onclick="cancel()"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
