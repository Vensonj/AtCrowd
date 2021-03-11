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
        // 给按钮组的更新节点按钮绑定单击响应函数
        $("#treeDemo").on("click", ".editBtn", function () {
            // 将当前节点的 id 保存到 全局变量
            window.id = this.id;
            // 打开更新节点的模态框
            $("#menuEditModal").modal("show");

            // 获取zTreeObj对象
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            // 根据 id 属性获取节点对象
            /*
            key:用来搜索节点的属性名
            value：用来搜索节点的属性值
             */
            var key = "id";
            var value = window.id;
            let currentNode = zTreeObj.getNodeByParam(key, value);
            // 回显表单数据
            $("#menuEditModal [name=name]").val(currentNode.name);
            $("#menuEditModal [name=url]").val(currentNode.url);
            // radio/CheckBox 回显需要将数据放在数组中
            $("#menuEditModal [name=icon]").val([currentNode.icon]);
            return false;
        })

        // 给更新节点的模态框的更新按钮绑定单击响应函数
        $("#menuEditBtn").click(function () {
            // 收集表单项中用户提交的数据
            var name = $.trim($("#menuEditModal [name=name]").val());
            var url = $("#menuEditModal [name=url]").val();
            // 单选按钮要定位到被选中的那一个
            var icon = $("#menuEditModal [name=icon]:checked").val();
            // 发送Ajax请求
            $.ajax({
                url: "menu/update",
                type: "post",
                dataType: "json",
                data: {
                    "id": window.id,
                    "name": name,
                    "url": url,
                    "icon": icon
                },
                success: function (response) {
                    if (response.result === "SUCCESS") {
                        layer.msg("更新节点操作成功")
                        // Refresh tree structure
                        generateTree();
                    }
                    if (response.result === "FAILED") {
                        layer.msg("更新节点操作失败 " + response.message)
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }

            })
            // close add Child node Modal box
            $("#menuEditModal").modal("hide");
        })

        // 给按钮组的删除节点按钮绑定单击响应函数
        $("#treeDemo").on("click", ".removeBtn", function () {
            // 将当前节点的 id 保存到 全局变量
            window.id = this.id;
            // 打开删除节点的模态框
            $("#menuConfirmModal").modal("show");
            // 获取zTreeObj对象
            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            // 根据 id 属性获取节点对象
            /*
            key:用来搜索节点的属性名
            value：用来搜索节点的属性值
             */
            var key = "id";
            var value = window.id;
            let currentNode = zTreeObj.getNodeByParam(key, value);
            // 回显表单数据
            $("#menuEditModal [name=name]").val(currentNode.name);

            // radio/CheckBox 回显需要将数据放在数组中
            $("#menuEditModal [name=icon]").val([currentNode.icon]);

            $("#removeNodeSpan").html("【<i class='" + currentNode.icon + "'></i>" + currentNode.name + "】")
            return false;
        })

        // 给删除节点的模态框的确定删除按钮绑定单击响应函数
        $("#confirmBtn").click(function () {
            // 发送Ajax请求
            $.ajax({
                url: "menu/remove",
                type: "post",
                dataType: "json",
                data: {
                    "id": window.id,
                },
                success: function (response) {
                    if (response.result === "SUCCESS") {
                        layer.msg("删除节点操作成功")
                        // Refresh tree structure
                        generateTree();
                    }
                    if (response.result === "FAILED") {
                        layer.msg("删除节点操作失败 " + response.message)
                    }
                },
                error: function (response) {
                    layer.msg(response.status + " " + response.statusText)
                }

            })
            // close add Child node Modal box
            $("#menuConfirmModal").modal("hide");
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
