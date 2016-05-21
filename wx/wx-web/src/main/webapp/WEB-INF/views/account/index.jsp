<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账户管理</title>
    <title>主页</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/account.js"></script>
    <script type="text/x-jquery-tmpl" id="tempBody">
         <tr>
            <td><input class="chkId" val="{{= id}}" type="checkbox"></td>
             <td>{{= id}}</td>
            <td>{{= name}}</td>
            <td>{{= appid}}</td>
            <td>{{= type}}</td>
        </tr>
    </script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="main-title">
        <span>账户管理</span>
    </div>
    <div class="panel panel-search">
        <div class="form-inline form-group">
            <label for="txtName">账号名</label>
            <input type="text" class="form-control" id="txtName" placeholder="账号名">
            <button type="button" id="btnSearch" class="btn btn-default">查询</button>
        </div>
    </div>
    <div class="panel-op">
        <a href="javascript:void(0)" onclick="add()">新增</a>
        <a href="javascript:void(0)" onclick="edit()">修改</a>
        <a href="javascript:void(0)" onclick="del()">删除</a>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="col-chk"></th>
            <th class="row-head">编号</th>
            <th class="row-head">名称</th>
            <th class="row-head">微信号</th>
            <th class="row-head">类型</th>
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
                    <h4 class="modal-title" id="myTitle">编辑账号</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="formAccount">
                        <input type="hidden" id="id">

                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="name">账号名:</label>

                            <div class="col-sm-9">
                                <input type="text" id="name" class="form-control" maxlength="50"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="appid">AppId:</label>

                            <div class="col-sm-9">
                                <input type="text" id="appid" class="form-control" maxlength="100"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="secret">AppSecret:</label>

                            <div class="col-sm-9">
                                <input type="text" id="secret" class="form-control" maxlength="100"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="type">公众号类型:</label>

                            <div class="col-sm-9">
                                <select id="type" class="form-control">
                                    <option value="-1">请选择</option>
                                    <option value="0">服务号</option>
                                    <option value="1">订阅号</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="remark">备注:</label>

                            <div class="col-sm-9">
                                <textarea id="remark" class="form-control" maxlength="200"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                    <button type="button" id="btnSave"
                            class="btn btn-primary">保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
