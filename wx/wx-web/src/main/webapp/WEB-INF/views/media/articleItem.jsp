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
    <title>消息项管理</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <script type="text/javascript" src="../../static/js/media/articleItem.js"></script>
    <script type="text/x-jquery-tmpl" id="tempBody">
        <tr>
            <td><input class="chkId" val="{{= id}}" type="checkbox"></td>
            <td>{{= id}}</td>
             <td>{{= articleId}}</td>
            <td>{{= title}}</td>
             <td>{{= digest}}</td>
            <td>{{= showCover?"是":"否"}}</td>
        </tr>

    </script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="main-title">
        <span>消息项管理</span>
        <input id="hidAid" type="hidden" value="${aid}"/>
    </div>
    <div class="panel-op">
        <a href="javascript:void(0)" onclick="add()">新增</a>&nbsp;&nbsp;
        <a href="javascript:void(0)" onclick="articleItemDetail()">编辑</a>&nbsp;&nbsp;
        <a href="javascript:void(0)" onclick="del()">删除</a>&nbsp;&nbsp;
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="col-chk"></th>
            <th class="row-head">id</th>
            <th class="row-head">素材id</th>
            <th class="row-head">标题</th>
            <th class="row-head">描述</th>
            <th class="row-head">是否封面</th>
        </tr>
        </thead>
        <tbody id="listBody">
        </tbody>
    </table>
    <jsp:include page="../shared/pager.jsp"></jsp:include>

    <%--确认--%>
    <div class="modal fade confirm" id="popConfirm" tabindex="-1" role="dialog"
         aria-labelledby="myTitle" aria-hidden="true">
        <div class="modal-dialog" style="width: 300px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">确认删除</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                    <button type="button" id="btnYes"
                            class="btn btn-primary">确定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
