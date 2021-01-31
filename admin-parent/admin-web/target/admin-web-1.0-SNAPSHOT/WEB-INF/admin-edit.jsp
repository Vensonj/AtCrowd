<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/24 0024
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="include-head.jsp" %>
<body>
<%@include file="include-topnavbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="<c:url value="/admin/toMainPage"/>">首页</a></li>
                <li><a href="<c:url value="/admin/get/page"/>">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form" action="<c:url value="/admin/update"/>" method="post">
                        <span style="color: red">${requestScope.exception.message}</span>
                        <input type="hidden" name="id" value="${admin.id}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <div class="form-group">
                            <label for="exampleInputPassword1">登录账号</label>
                            <input type="text" name="loginAcct" class="form-control" id="exampleInputPassword1" value="${admin.loginAcct}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户昵称</label>
                            <input type="text" name="userName" class="form-control" id="exampleInputPassword1" value="${admin.userName}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input type="email" name="email" class="form-control" id="exampleInputEmail1" value="${admin.email}">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
