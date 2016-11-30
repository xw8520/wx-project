<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>标签管理</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <script type="text/javascript" src="../../static/js/tag/tag.js"></script>
    <script type="text/x-jquery-tmpl" id="tempBody">
        <tr>
            <td><input class="chkId" val="{{= id}}" type="checkbox"></td>
            <td>{{= id}}</td>
            <td>{{= name}}</td>
            <td>{{= wxTagId}}</td>
            <td>{{= accountName}}</td>
            <td>{{= remark}}</td>
        </tr>

    </script>
    <script type="text/x-jquery-tmpl" id="tempSelc">
        <option value="{{= id}}">{{= name}}</option>
    </script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="main-title">
        <span>标签管理</span>
        <a href="#" class="glyphicon glyphicon-new-window"
           style="float: right;margin-right: 30px;" target="_blank"></a>
    </div>
    <div class="panel panel-search">
        <div class="form-inline form-group">
            <label for="txtName">标签名：</label>
            <input type="text" class="form-control" id="txtName" placeholder="标题">
            &nbsp;&nbsp;
            <label for="selAccount">公众号：</label>
            <select id="selAccount" class="form-control" style="width: 150px">
            </select>
            &nbsp;&nbsp;
            <button type="button" id="btnSearch" class="btn btn-default">查询</button>
        </div>
    </div>
    <div class="panel-op">
        <a href="javascript:void(0)" onclick="add()">新增</a>
        <a href="javascript:void(0)" onclick="edit()">编辑</a>
        <a href="javascript:void(0)" onclick="del()">删除</a>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="col-chk"></th>
            <th class="row-head">id</th>
            <th class="row-head">标签名</th>
            <th class="row-head">微信标签ID</th>
            <th class="row-head">公众号</th>
            <th class="row-head">备注</th>
        </tr>
        </thead>
        <tbody id="listBody">
        </tbody>
    </table>
    <jsp:include page="../shared/pager.jsp"></jsp:include>

    <!-- Modal -->
    <div class="modal fade" id="popModel" tabindex="-1" role="dialog"
         aria-labelledby="myTitle" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myTitle">标签管理</h4>
                </div>
                <div class="modal-body">
                    <div class="form-horizontal" id="formMedia">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="txtTitle">标签名:</label>
                            <div class="col-sm-9">
                                <input type="hidden" id="hidId"/>
                                <input type="text" id="txtTitle" class="form-control" maxlength="50"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">微信Id:</label>
                            <div class="col-sm-9">
                                <input type="number" id="txtWxId" class="form-control" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="txtAccount">公众号:</label>
                            <div class="col-sm-9">
                                <select id="txtAccount" class="form-control"></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="txtRemark">备注:</label>

                            <div class="col-sm-9">
                                <textarea id="txtRemark" class="form-control" maxlength="200"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                    <button type="button" id="btnAdd"
                            class="btn btn-primary">保存
                    </button>
                </div>
            </div>
        </div>
    </div>

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
                            class="btn btn-primary">保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>