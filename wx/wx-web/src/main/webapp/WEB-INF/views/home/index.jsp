<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <title>登录</title>
    <link rel="stylesheet" href="../../static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../static/css/all.css" type="text/css"/>
    <link rel="stylesheet" href="../../static/css/login.css" type="text/css"/>

    <script type="text/javascript" src="../../static/js/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/login.js"></script>

</head>

<body>
<div class="container">
    <form class="form-signin">
        <h2 class="form-signin-heading">登录</h2>
        <input type="text" id="txtAccount" class="form-control" placeholder="账号"/>

        <input type="password" id="txtPassword" class="form-control" placeholder="密码"/>

        <button class="btn btn-lg btn-primary btn-block" type="button"
        id="btnLogin">登录</button>
    </form>
</div>
</body>


</html>
