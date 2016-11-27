<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>素材管理</title>
    <jsp:include page="../shared/header.jsp"></jsp:include>
    <script type="text/javascript" src="../../static/js/pager.js"></script>
    <script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <script type="text/javascript" src="../../static/js/media/media.js"></script>
    <script type="text/x-jquery-tmpl" id="tempBody">
        <tr>
            <td><input class="chkId" val="{{= id}}" type="checkbox"></td>
            <td>{{= title}}</td>
            <td>{{= mediatype}}</td>
            <td>{{= permanent?"是":"否"}}</td>
            <td>{{= mediaid}}</td>
            <td>{{= account}}</td>
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
        <span>临时素材管理</span>
    </div>
    <div class="panel panel-search">
        <div class="form-inline form-group">
            <label for="txtName">标题：</label>
            <input type="text" class="form-control" id="txtName" placeholder="标题">
            &nbsp;&nbsp;
            <label for="selAccount">公众号：</label>
            <select id="selAccount" class="form-control" style="width: 150px">
            </select>
            &nbsp;&nbsp;
            <label for="selPermanent">有效期：</label>
            <select id="selPermanent" class="form-control" style="width: 150px">
                <option value="0">临时</option>
                <option value="1">永久</option>
            </select>
            &nbsp;&nbsp;
            <label for="selType">类型：</label>
            <select id="selType" class="form-control" style="width: 150px">
                <option value="0">图片</option>
                <option value="1">语音</option>
                <option value="2">视频</option>
                <option value="3">缩略图</option>
            </select>
            &nbsp;&nbsp;
            <button type="button" id="btnSearch" class="btn btn-default">查询</button>
        </div>
    </div>
    <div class="panel-op">
        <a href="javascript:void(0)" onclick="add()">新增</a>
        <a href="javascript:void(0)" onclick="mediaDetail()">详情</a>
        <a href="javascript:void(0)" onclick="del()">删除</a>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th class="col-chk"></th>
            <th class="row-head">标题</th>
            <th class="row-head">素材类型</th>
            <th class="row-head">永久素材</th>
            <th class="row-head">素材id</th>
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
                    <h4 class="modal-title" id="myTitle">添加素材</h4>
                </div>
                <div class="modal-body">
                    <div class="form-horizontal" id="formMedia">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="txtTitle">标题:</label>
                            <div class="col-sm-9">
                                <input type="text" id="txtTitle" class="form-control" maxlength="50"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">有效期:</label>
                            <div class="col-sm-9">
                                <select id="txtPermanent" class="form-control">
                                    <option value="0">临时</option>
                                    <option value="1">永久</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">素材类型:</label>
                            <div class="col-sm-9">
                                <select id="selType2" class="form-control" style="width: 150px">
                                    <option value="0">图片</option>
                                    <option value="1">语音</option>
                                    <option value="2">视频</option>
                                    <option value="3">缩略图</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="txtAccount">公众号:</label>
                            <div class="col-sm-9">
                                <select id="txtAccount" class="form-control"></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="txtFile">素材:</label>

                            <div class="col-sm-9">
                                <form id="fromUpload" enctype="multipart/form-data">
                                    <input id="txtFile" name="file" type="file" style="display:none">
                                    <input id="hidFile" type="hidden"/>

                                    <div class="input-append">
                                        <input id="txtFileName" class="text-input"
                                               style="height: 30px;width: 250px;"
                                               type="text" readonly>
                                        <a class="btn btn-primary" onclick="$('input[id=txtFile]').click();">
                                            选择文件</a>
                                        <a class="btn btn-primary" id="btnUpload">&nbsp;上&nbsp;&nbsp;传&nbsp;</a>
                                    </div>
                                </form>
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
