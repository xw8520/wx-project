<%--
  Created by Admin on 2016/3/13.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<script type="text/javascript" src="../../../static/js/jquery-1.12.1.min.js"></script>
<script type="text/javascript" src="../../../static/js/jquery-form.js"></script>
<script type="text/javascript">
    $(function () {
        $('#btnUpload').click(function () {
            $("#uploadForm").ajaxSubmit({
                url: "/upload.do",
                type: "POST",
                beforeSend: function (data) { //取回数据前

                },
                success: function (data) {
                }
            });
        });

        $('#btnSendMsg').click(function () {
            var data = {};
            data.message = $('#message').val();
            $.ajax({
                url: '/sendMsg.do',
                type: 'POST',
                data: data,
                dataType: 'json',
                success: function (resp) {
                    alert(resp);
                },
                error: function (resp) {
                    alert('error');
                }
            })
        });
    });
</script>
<body>

<form id="uploadForm" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="button" id="btnUpload" value="上传"/>
</form>

<a href="/download.html" target="_blank">文件下载</a>

<input type="text" id="message"/>
<input type="button" id="btnSendMsg"/>
</body>
</html>
