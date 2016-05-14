<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>菜单管理</title>
    <title>主页</title>
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../static/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../../static/css/all.css" type="text/css"/>
    <script type="text/javascript" src="../../static/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/sys/menu.js"></script>
</head>
<body>
<div class="panel panel-body main-content">
    <div class="main-title">
        <span>菜单管理</span>
    </div>
    <div class="panel-op">
        <a href="javascript:void(0)" onclick="addMenu(0)">新增</a>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="row-head" style="width: 400px">菜单名</th>
            <th class="row-head">类型</th>
            <th class="row-head">Url</th>
            <th class="row-head">操作</th>
        </tr>
        </thead>
        <tbody id="listBody">
        <c:forEach var="item" items="${menu}">
            <tr>
                <td>
                    <i class="glyphicon glyphicon-minus gp" val="${item.id}"></i>
                        ${item.name}
                </td>
                <td>${item.type==0?"组":"菜单"}</td>
                <td>${item.url}</td>
                <td>
                    <i class="glyphicon glyphicon-plus" title="新增" onclick="addMenu(${item.id})"></i>&nbsp;&nbsp;&nbsp;&nbsp;
                    <i class="glyphicon glyphicon-edit" title="修改" onclick="editMenu(${item.id})"></i>&nbsp;&nbsp;&nbsp;&nbsp;
                    <i class="glyphicon glyphicon-remove" title="删除" onclick="deleteMenu(${item.id})"></i>
                </td>
            </tr>
            <c:if test="${item.child!=null}">
                <c:forEach var="child" items="${item.child}">
                    <tr pid="${item.id}">
                        <td style="padding-left: 30px;">
                            <i></i>
                                ${child.name}
                        </td>
                        <td>${child.type==0?"组":"菜单"}</td>
                        <td>${child.url}</td>
                        <td>
                            <i class="glyphicon glyphicon-edit" onclick="editMenu(${child.id})"></i>&nbsp;&nbsp;&nbsp;&nbsp;
                            <i class="glyphicon glyphicon-remove" onclick="deleteMenu(${child.id})"></i>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

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
