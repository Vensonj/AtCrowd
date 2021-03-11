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
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="self/my-menu.js"></script>
<script>
    $(function () {
        // 调用封装好的专门用于初始化树形结构的函数
        generateTree();
        // 给按钮组的添加子节点按钮绑定单击响应函数
        $("#treeDemo").on("click", ".addBtn", function () {
            // 将当前节点的 id 作为 新节点的 pid 保存到 全局变量，并存入到数据库
            window.pid = this.id;
            // 打开添加新节点的模态框
            $("#menuAddModal").modal("show");
            return false;
        })
        // 给添加子节点的模态框的保存按钮绑定单击响应函数
        $("#menuSaveBtn").click(function () {
            // 收集表单项中用户提交的数据
            var name = $.trim($("#menuAddModal [name=name]").val());
            var url = $("#menuAddModal [name=url]").val();
            // 单选按钮要定位到被选中的那一个
            var icon = $("#menuAddModal [name=icon]:checked").val();
            // 发送Ajax请求
            $.ajax({
                url: "menu/save",
                type: "post",
                dataType: "json",
                data: {
                    "pid": window.pid,
                    "name": name,
                    "url": url,
                    "icon": icon
                },
                success: function (response) {
                    if (response.result === "SUCCESS") {
                        layer.msg("添加子节点操作成功")
                        // Refresh tree structure
                        generateTree();
                    }
                    if (response.result === "FAILED") {
                        layer.msg("添加子节点操作失败 " + response.message)
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }

            })
            // close add Child node Modal box
            $("#menuAddModal").modal("hide");


            // Click the reset button to Clear form
            $("#menuResetBtn").click();
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
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree">

                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<%--引入点击按钮组弹出对应模态框的文件--%>
<%@include file="modal-menu-add.jsp" %>
<%@include file="modal-menu-edit.jsp" %>
<%@include file="modal-menu-confirm.jsp" %>
</html>
