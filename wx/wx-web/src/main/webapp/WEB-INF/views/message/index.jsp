<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/12/2
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>群发消息</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <link rel="stylesheet" href="../../static/js/chosen/chosen.min.css"/>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <script type="text/javascript" src="../../static/js/message/index.js"></script>
    <script type="text/javascript" src="../../static/js/chosen/chosen.jquery.min.js"></script>
    <script type="text/x-jquery-tmpl" id="tempBody">
        <tr>
            <td><input class="chkId" data-id="{{= id}}" type="checkbox"></td>
            <td>{{= title}}</td>
            <td>{{= typeName}}</td>
            <td>{{= accountName}}</td>
            <td>{{= tagName}}</td>
            <td>{{= stateName}}</td>
            <td>{{= toall?"是":"否"}}</td>
            <td>{{= remark}}</td>
            <td style="width:180px;">
                <a href="javascript:void(0)" onclick="edit({{= id}})">修改</a>&nbsp;
                <a href="javascript:void(0)" onclick="syncState({{= id}})">同步</a>&nbsp;
                <a href="javascript:void(0)" onclick="send({{= id}})">发送</a>&nbsp;
                <a href="javascript:void(0)" onclick="del({{= id}})">删除</a>
            </td>
        </tr>
    </script>
    <script type="text/x-jquery-tmpl" id="tempSelc">
        <option value="{{= id}}">{{= name}}</option>
    </script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="main-title">
        <span>群发消息</span>
        <a href="#" class="glyphicon glyphicon-new-window"
           style="float: right;margin-right: 30px;" target="_blank"></a>
    </div>
    <div class="panel panel-search">
        <div class="form-inline form-group">
            <label for="txtTitle">标题：</label>
            <input type="text" class="form-control" style="width: 150px" id="txtTitle" placeholder="标题">
            &nbsp;&nbsp;
            <label for="selAccount">公众号：</label>
            <select id="selAccount" class="form-control" style="width: 150px">
            </select>
            &nbsp;&nbsp;
            <label for="selType">类型：</label>
            <select id="selType" class="form-control" style="width: 150px">
            </select>
            &nbsp;&nbsp;
            <label for="selState">状态：</label>
            <select id="selState" class="form-control" style="width: 150px">
            </select>
            &nbsp;&nbsp;
            <button type="button" id="btnSearch" class="btn btn-default">查询</button>
        </div>
    </div>
    <div class="panel-op">
        <a href="javascript:void(0)" onclick="add()">新增</a>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="col-chk"></th>
            <th class="row-head">标题</th>
            <th class="row-head">消息类型</th>
            <th class="row-head">所属账号</th>
            <th class="row-head">标签名</th>
            <th class="row-head">状态</th>
            <th class="row-head">全部</th>
            <th class="row-head">备注</th>
            <th class="row-head" style="width:120px!important;">操作</th>
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
                    <h4 class="modal-title" id="myTitle">添加消息</h4>
                </div>
                <div class="modal-body">
                    <div class="form-horizontal" id="formMedia">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="txtTitle">标题:</label>
                            <div class="col-sm-9">
                                <input type="hidden" id="id"/>
                                <input type="hidden" id="domain"/>
                                <input type="text" id="title" class="form-control" maxlength="50"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="mediaId">素材id</label>
                            <div class="col-sm-9">
                                <input type="text" id="mediaId" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">消息类型:</label>
                            <div class="col-sm-9">
                                <select id="selType2" class="form-control">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="accountId">公众号:</label>
                            <div class="col-sm-9">
                                <select id="accountId" class="form-control"></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="tagId">标签</label>
                            <div class="col-sm-9">
                                <select type="text" id="tagId">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="toall">发送全部
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="fContent">
                            <label class="col-sm-2 control-label" for="content">消息内容:</label>
                            <div class="col-sm-9">
                                <textarea id="content" class="form-control" maxlength="200"></textarea>
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