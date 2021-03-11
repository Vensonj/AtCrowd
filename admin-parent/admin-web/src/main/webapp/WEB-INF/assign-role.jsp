<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/22
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh_CN">
<%@include file="include-head.jsp" %>
<script type="text/javascript">
    $(function () {

        $("#toRigthBtn").click(function () {
            $("select:eq(0)>option:selected").appendTo("select:eq(1)")
        })

        $("#toLeftBtn").click(function () {
            $("select:eq(1)>option:selected").appendTo("select:eq(0)")
        })

        $("#assignBtn").click(function () {
            // 在提交表单前，把“已分配”区域的所有option选中
            $("select:eq(1)>option").prop("selected", "selected");
        })
    })
</script>
<body>
<%@include file="include-topnavbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form role="form" class="form-inline" method="post" action="/assign/doAssignRole">
                        <input type="hidden" name="adminId" value="${param.adminId}">
                        <input type="hidden" name="pageNum" value="${param.pageNum}">
                        <input type="hidden" name="keyword" value="${param.keyword}">
                        <div class="form-group">
                            <%--@declare id="exampleinputpassword1"--%><label
                                for="exampleInputPassword1">未分配角色列表</label><br>
                            <select class="form-control" multiple="multiple" size="10"
                                    style="width:150px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRigthBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="multiple" size="10"
                                    style="width:150px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="assignBtn" type="submit" style="width: 150px"
                                class="btn btn-lg btn-success btn-block">Save
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
