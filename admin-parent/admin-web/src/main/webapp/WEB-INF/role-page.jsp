<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/27 0027
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css">
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="self/my-role.js"></script>
<script type="text/javascript">
    $(function () {
        // 1、为分页操作准备初始化数据
        window.pageNum = 1;
        window.pageSize = 7;
        window.keyword = "";
        // 2、调用分页函数，实现分页效果
        generatePage();
        // 3、给查询按钮绑定单击函数
        $("#searchBtn").click(function () {
            window.keyword = $("#keywordInput").val();
            generatePage();
        })
        //4、 点击新增按钮弹出模态框
        $("#addBtn").click(function () {
            $("#addModal").modal("show")
        })
        //5、 点击模态框的保存按钮保存数据到数据库
        $("#savaRoleBtn").click(function () {
            // 获取要添加的角色名
            let roleName = $.trim($("#addModal [name=roleName]").val());
            $.ajax({
                url: "role/saveRole",
                type: "post",
                data: {
                    name: roleName
                },
                dataType: "json",
                success: function (response) {
                    var result = response.result;
                    if (result === "SUCCESS") {
                        layer.msg("添加数据成功")
                        // 如果添加成功，重新加载分页，并定位到新增数据
                        window.pageNum = 999999;
                        generatePage();
                    }
                    if (result === "FAILED") {
                        layer.msg("添加数据失败" + response.message)
                    }
                },
                error: function (response) {
                    layer.msg(response.status + "" + response.text)
                }
            })
            // 关闭模态框
            $("#addModal").modal("hide");
            // 清理模态框中的内容
            $("#addModal [name=roleName]").val("");
        })

        // 6、给“铅笔按钮”即更新按钮绑定单击事件函数,因为这个图标是动态生成的，每次翻页绑定的单击函数都会消失
        // 所以使用Jquery的on函数解决，找到动态生成的内容的父级静态
        // on 函数的第一个参数是 要执行的时间类型
        // on 函数的第二个参数是 要绑定单击事件的元素选择器
        $("#rolePageBody").on("click", ".pencilBtn", function () {
            // 弹出更新的模态框
            $("#editModal").modal("show");
            // 获取要更新的角色名（数据回显）使用Dom来获取 this 代表当前对象，这里就是当前的更新按钮
            let roleName = $(this).parent().prev().text();
            window.roleId = this.id;
            // 将数据回显到模态框
            $("#editModal [name=roleName]").val(roleName);
        })
        //7、点击模态框的更新按钮更新数据到数据库
        $("#updateRoleBtn").click(function () {
            // 从文本框中取出角色名
            let roleName = $.trim($("#editModal [name=roleName]").val());
            $.ajax({
                url: "role/updateRole",
                type: "post",
                data: {
                    id: window.roleId,
                    name: roleName,
                },
                dataType: "json",
                success: function (response) {
                    var result = response.result;
                    if (result === "SUCCESS") {
                        layer.msg("更新数据成功")
                        // 如果添加成功，重新加载分页
                        generatePage();
                    }
                    if (result === "FAILED") {
                        layer.msg("更新操作失败" + response.message)
                    }
                },
                error: function (response) {
                    layer.msg(response.status + "" + response.text)
                }
            })
            // 关闭模态框
            $("#editModal").modal("hide");
        })

    })
</script>
<body>
<%@include file="include-topnavbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button id="addBtn" type="button" class="btn btn-primary" style="float:right"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody id="rolePageBody"></tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="modal-role-add.jsp" %>
<%@include file="modal-role-edit.jsp" %>
</html>
