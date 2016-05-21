<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>临时素材管理</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/media/tmedia.js"></script>
    <script type="text/x-jquery-tmpl" id="tempBody">
        <tr>
            <td><input class="chkId" val="{{= id}}" type="checkbox"></td>
            <td>{{= title}}</td>
            <td>{{= mediatype}}</td>
            <td>{{= mediaid}}</td>
            <td>{{= remark}}</td>
        </tr>
    </script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="main-title">
        <span>临时素材管理</span>
    </div>
    <div class="panel panel-search">
        <div class="form-inline form-group">
            <label for="txtName">标题</label>
            <input type="text" class="form-control" id="txtName" placeholder="标题">
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
            <th class="row-head">标题</th>
            <th class="row-head">素材类型</th>
            <th class="row-head">素材id</th>
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
                    <h4 class="modal-title" id="myTitle">编辑菜单</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="formAccount">
                        <input type="hidden" id="id">
                        <input type="hidden" id="pid">

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="name">菜单名:</label>

                            <div class="col-sm-9">
                                <input type="text" id="name" class="form-control" maxlength="50"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="type">类型:</label>

                            <div class="col-sm-9">
                                <%--<input type="text" id="type" class="form-control" maxlength="100"/>--%>
                                <select id="type" class="form-control">
                                    <option value="0">组</option>
                                    <option value="1">菜单</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="url">Url:</label>

                            <div class="col-sm-9">
                                <input type="text" id="url" class="form-control" maxlength="100"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="ordernum">排序:</label>

                            <div class="col-sm-9">
                                <input type="number" id="ordernum" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="status">状态:</label>

                            <div class="col-sm-9">
                                <select id="status" class="form-control">
                                    <option value="1">正常</option>
                                    <option value="2">锁定</option>
                                </select>
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
