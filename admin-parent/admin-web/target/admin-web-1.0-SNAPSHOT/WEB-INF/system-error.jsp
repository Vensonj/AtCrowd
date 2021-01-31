<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/16 0016
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">

    <title>Login页面</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container" style="text-align: center">
    <h2 class="form-signin-heading">
        <i class="glyphicon glyphicon-log-in"></i> 尚筹网系统消息
    </h2>
    <%--requestScope对应的是request域数据中的Map
    requestScope.exception相当于request.getAttribute("exception")
    requestScope.exception.message相当于exception.getMessage()
    --%>
    <h3 style="text-align: center">出错原因：${requestScope.exception.message}</h3>
    <button id="btn1" class="btn btn-lg btn-success btn-block" style="width: 150px; margin: 50px auto 0 auto">点我返回上一页
    </button>

</div>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $("#btn1").click(function () {
            //相当于浏览器的后退按钮
            window.history.back();
        })
    })
</script>
</body>
</html>
